package com.klef.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.example.entity.Counsellor;

@Repository
public interface CounsellorRepository extends JpaRepository<Counsellor, Long> {
    Counsellor findByEmail(String email);
}