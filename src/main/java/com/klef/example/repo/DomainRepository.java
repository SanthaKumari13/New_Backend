package com.klef.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.example.entity.Domain;

public interface DomainRepository extends JpaRepository<Domain, Long> {}
