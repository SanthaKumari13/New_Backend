package com.klef.example.controller;

import com.klef.example.entity.CounselorDomainAssignment;
import com.klef.example.services.CounselorDomainAssignmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/assignments")
public class CounselorDomainAssignmentController {

    @Autowired
    private CounselorDomainAssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<CounselorDomainAssignment> assignCounselorToDomain(
            @RequestParam Long counselorId,
            @RequestParam Long domainId) {
        CounselorDomainAssignment assignment = assignmentService.assignCounselorToDomain(counselorId, domainId);
        return ResponseEntity.ok(assignment);
    }

    @GetMapping
    public ResponseEntity<List<CounselorDomainAssignment>> getAllAssignments() {
        List<CounselorDomainAssignment> assignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/domain/{domainId}")
    public ResponseEntity<List<CounselorDomainAssignment>> getAssignmentsByDomainId(@PathVariable Long domainId) {
        List<CounselorDomainAssignment> assignments = assignmentService.getAssignmentsByDomainId(domainId);
        return ResponseEntity.ok(assignments);
    }
}
