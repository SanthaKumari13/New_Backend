package com.klef.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.example.entity.Program;

public interface ProgramRepository extends JpaRepository<Program, Long> {}
