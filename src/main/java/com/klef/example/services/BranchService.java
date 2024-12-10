package com.klef.example.services;


import com.klef.example.entity.Branch;
import com.klef.example.repo.BranchRepository;
import com.klef.example.repo.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProgramRepository programRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElseThrow(() -> new RuntimeException("Branch not found"));
    }

    public Branch addBranch(Branch branch, Long programId) {
        branch.setProgram(programRepository.findById(programId).orElseThrow(() -> new RuntimeException("Program not found")));
        return branchRepository.save(branch);
    }

    public Branch updateBranch(Long id, Branch updatedBranch) {
        Branch branch = getBranchById(id);
        branch.setName(updatedBranch.getName());
        return branchRepository.save(branch);
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}

