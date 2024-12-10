package com.klef.example.services;

import com.klef.example.entity.Branch;
import com.klef.example.entity.Counsellor;
import com.klef.example.entity.Domain;
import com.klef.example.entity.Program;
import com.klef.example.repo.BranchRepository;
import com.klef.example.repo.CounsellorRepository;
import com.klef.example.repo.DomainRepository;
import com.klef.example.repo.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class DomainService {

    @Value("${upload.directory}")
    private String uploadDir; // Root directory for uploads (e.g., /uploads)

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProgramRepository programRepository;
    
    @Autowired
    private CounsellorRepository counsellorRepository;

    public List<Domain> getAllDomains() {
        return domainRepository.findAll();
    }

    public Domain getDomainById(Long id) {
        return domainRepository.findById(id).orElseThrow(() -> new RuntimeException("Domain not found with id: " + id));
    }

    public Domain addDomain(Domain domain, Long branchId, Long programId, MultipartFile demoVideo,
                            MultipartFile roadmap, List<MultipartFile> certifications) throws IOException {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchId));
        Program program = programRepository.findById(programId).orElseThrow(() -> new RuntimeException("Program not found with id: " + programId));

        domain.setBranch(branch);
        domain.setProgram(program);

        if (demoVideo != null && !demoVideo.isEmpty()) {
            domain.setDemoVideo(saveFile(demoVideo, "videos"));
        }
        if (roadmap != null && !roadmap.isEmpty()) {
            domain.setRoadmap(saveFile(roadmap, "roadmaps"));
        }
        if (certifications != null && !certifications.isEmpty()) {
            List<String> certificationPaths = certifications.stream()
                    .map(file -> {
                        try {
                            return saveFile(file, "certifications");
                        } catch (IOException e) {
                            throw new RuntimeException("Error saving certification image", e);
                        }
                    }).toList();
            domain.setCertifications(certificationPaths);
        }

        return domainRepository.save(domain);
    }

    public Domain updateDomain(Long id, Domain updatedDomain, Long branchId, Long programId,
                               MultipartFile demoVideo, MultipartFile roadmap,
                               List<MultipartFile> certifications) throws IOException {
        Domain domain = getDomainById(id);

        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchId));
        Program program = programRepository.findById(programId).orElseThrow(() -> new RuntimeException("Program not found with id: " + programId));

        domain.setName(updatedDomain.getName());
        domain.setDescription(updatedDomain.getDescription());
        domain.setResourcesLinks(updatedDomain.getResourcesLinks());
        domain.setBranch(branch);
        domain.setProgram(program);

        if (demoVideo != null && !demoVideo.isEmpty()) {
            domain.setDemoVideo(saveFile(demoVideo, "videos"));
        }
        if (roadmap != null && !roadmap.isEmpty()) {
            domain.setRoadmap(saveFile(roadmap, "roadmaps"));
        }
        if (certifications != null && !certifications.isEmpty()) {
            List<String> certificationPaths = certifications.stream()
                    .map(file -> {
                        try {
                            return saveFile(file, "certifications");
                        } catch (IOException e) {
                            throw new RuntimeException("Error saving certification image", e);
                        }
                    }).toList();
            domain.setCertifications(certificationPaths);
        }

        return domainRepository.save(domain);
    }

    public void deleteDomain(Long id) {
        domainRepository.deleteById(id);
    }

    private String saveFile(MultipartFile file, String subDir) throws IOException {
        Path uploadPath = Paths.get(uploadDir, subDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        file.transferTo(filePath.toFile());

        return subDir + "/" + fileName;
    }
   

}
