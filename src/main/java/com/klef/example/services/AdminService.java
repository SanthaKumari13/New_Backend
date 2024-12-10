package com.klef.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.example.entity.Admin;
import com.klef.example.repo.AdminRepository;



@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean authenticateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }
    
}
