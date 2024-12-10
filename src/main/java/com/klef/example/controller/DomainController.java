package com.klef.example.controller;

import com.klef.example.entity.Domain;
import com.klef.example.services.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/domains")
public class DomainController {

    @Autowired
    private DomainService domainService;

    @GetMapping
    public ResponseEntity<List<Domain>> getAllDomains() {
        return ResponseEntity.ok(domainService.getAllDomains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domain> getDomainById(@PathVariable Long id) {
        return ResponseEntity.ok(domainService.getDomainById(id));
    }

    @PostMapping
    public ResponseEntity<Domain> addDomain(@RequestPart("domain") Domain domain,
                                            @RequestParam Long branchId,
                                            @RequestParam Long programId,
                                            @RequestPart(value = "demoVideo", required = false) MultipartFile demoVideo,
                                            @RequestPart(value = "roadmap", required = false) MultipartFile roadmap,
                                            @RequestPart(value = "certifications", required = false) List<MultipartFile> certifications) throws IOException {
        Domain savedDomain = domainService.addDomain(domain, branchId, programId, demoVideo, roadmap, certifications);
        return ResponseEntity.ok(savedDomain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Domain> updateDomain(@PathVariable Long id,
                                               @RequestPart("domain") Domain domain,
                                               @RequestParam Long branchId,
                                               @RequestParam Long programId,
                                               @RequestPart(value = "demoVideo", required = false) MultipartFile demoVideo,
                                               @RequestPart(value = "roadmap", required = false) MultipartFile roadmap,
                                               @RequestPart(value = "certifications", required = false) List<MultipartFile> certifications) throws IOException {
        Domain updatedDomain = domainService.updateDomain(id, domain, branchId, programId, demoVideo, roadmap, certifications);
        return ResponseEntity.ok(updatedDomain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDomain(@PathVariable Long id) {
        domainService.deleteDomain(id);
        return ResponseEntity.ok("Domain deleted successfully");
    }
}
