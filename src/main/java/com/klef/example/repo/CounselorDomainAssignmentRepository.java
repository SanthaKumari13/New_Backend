package com.klef.example.repo;

import com.klef.example.entity.CounselorDomainAssignment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselorDomainAssignmentRepository extends JpaRepository<CounselorDomainAssignment, Long> {
    // Custom query methods can be added here if needed
    Optional<CounselorDomainAssignment> findByDomain_Id(Long domainId);
    List<CounselorDomainAssignment> findAllByDomain_Id(Long domainId);
}

