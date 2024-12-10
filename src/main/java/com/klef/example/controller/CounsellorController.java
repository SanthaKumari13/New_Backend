package com.klef.example.controller;


import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.example.entity.Counsellor;
import com.klef.example.services.CounsellorService;
import com.klef.example.utils.CSVParser;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    @Autowired
    private CSVParser csvParser;

    // Injecting the upload directory path from application.properties
    @Value("${upload.directory}")
    private String uploadDirectory;
 // Add the new endpoint to view a counselor by ID
    @GetMapping("/counselor/{id}")
    public ResponseEntity<?> getCounselorById(@PathVariable String id) {
        try {
            Long counselorId = Long.parseLong(id);
            Counsellor counselor = counsellorService.getCounselorById(counselorId);
            if (counselor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Counselor not found");
            }
            return ResponseEntity.ok(counselor);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid counselor ID format");
        }
    }





    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Uploaded file is empty!", HttpStatus.BAD_REQUEST);
        }

        try {
            // Save the file to the configured upload directory
            Path filePath = Paths.get(uploadDirectory + file.getOriginalFilename());
            Files.createDirectories(filePath.getParent()); // Ensure the directory exists
            Files.write(filePath, file.getBytes()); // Save the file

            // Process the file
            try (InputStream inputStream = Files.newInputStream(filePath)) {
                List<Counsellor> counsellors = csvParser.parse(inputStream);
                counsellorService.registerCounsellors(counsellors);
            }

            return new ResponseEntity<>("Counsellors registered successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/counselors")
    public ResponseEntity<List<Counsellor>> getAllCounselors() {
        List<Counsellor> counselors = counsellorService.getAllCounsellors();
        return ResponseEntity.ok(counselors);
    }

    // Counselor login
    @PostMapping("/counselor/login")
    public ResponseEntity<?> login(@RequestBody Counsellor counselor) {
        Counsellor authenticatedCounselor = counsellorService.authenticateCounsellor(counselor.getEmail(), counselor.getPassword());
        if (authenticatedCounselor != null) {
            // Return the counselor ID for use in frontend session
            return ResponseEntity.ok(authenticatedCounselor.getId());
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

   

}
