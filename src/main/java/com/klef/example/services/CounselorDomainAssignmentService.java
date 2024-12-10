package com.klef.example.services;

import com.klef.example.entity.CounselorDomainAssignment;
import com.klef.example.entity.Counsellor;
import com.klef.example.entity.Domain;
import com.klef.example.repo.CounselorDomainAssignmentRepository;
import com.klef.example.repo.CounsellorRepository;
import com.klef.example.repo.DomainRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounselorDomainAssignmentService {

    @Autowired
    private CounselorDomainAssignmentRepository assignmentRepository;

    @Autowired
    private CounsellorRepository counsellorRepository;

    @Autowired
    private DomainRepository domainRepository;

    public CounselorDomainAssignment assignCounselorToDomain(Long counselorId, Long domainId) {
        Counsellor counsellor = counsellorRepository.findById(counselorId)
                .orElseThrow(() -> new RuntimeException("Counselor not found with id: " + counselorId));
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new RuntimeException("Domain not found with id: " + domainId));

        CounselorDomainAssignment assignment = new CounselorDomainAssignment();
        assignment.setCounsellor(counsellor);
        assignment.setDomain(domain);

        return assignmentRepository.save(assignment);
    }

    public List<CounselorDomainAssignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public List<CounselorDomainAssignment> getAssignmentsByDomainId(Long domainId) {
        return assignmentRepository.findAllByDomain_Id(domainId);
    }
}
