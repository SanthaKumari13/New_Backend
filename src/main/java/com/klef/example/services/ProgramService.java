package com.klef.example.services;


import com.klef.example.entity.Program;
import com.klef.example.repo.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Program getProgramById(Long id) {
        return programRepository.findById(id).orElseThrow(() -> new RuntimeException("Program not found"));
    }

    public Program addProgram(Program program) {
        return programRepository.save(program);
    }

    public Program updateProgram(Long id, Program updatedProgram) {
        Program program = getProgramById(id);
        program.setName(updatedProgram.getName());
        return programRepository.save(program);
    }

    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }
}

