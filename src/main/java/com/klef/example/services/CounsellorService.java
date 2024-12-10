package com.klef.example.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.example.entity.Counsellor;
import com.klef.example.entity.Domain;
import com.klef.example.repo.CounsellorRepository;
import com.klef.example.repo.DomainRepository;


@Service
public class CounsellorService {

    @Autowired
    private CounsellorRepository counsellorRepository;
    @Autowired
    private DomainRepository domainRepository;

    public Counsellor findCounsellorById(Long id) {
        return counsellorRepository.findById(id).orElse(null);
    }
    // Register counsellors from a list
    public void registerCounsellors(List<Counsellor> counsellors) {
        counsellorRepository.saveAll(counsellors);
    }

    // Get all counsellors
    public List<Counsellor> getAllCounsellors() {
        return counsellorRepository.findAll();
    }
 // Add the service logic to fetch a counselor by ID
    public Counsellor getCounselorById(Long id) {
        return counsellorRepository.findById(id).orElse(null);
    }


    // Authenticate counsellor
    public Counsellor authenticateCounsellor(String email, String password) {
        Counsellor counsellor = counsellorRepository.findByEmail(email);
        if (counsellor != null && counsellor.getPassword().equals(password)) {
            return counsellor; // Authentication successful
        }
        return null; // Authentication failed
    }
    

}
