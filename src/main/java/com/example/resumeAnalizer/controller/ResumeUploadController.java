package com.example.resumeAnalizer.controller;

import java.time.LocalDateTime;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.resumeAnalizer.model.ResumeData;
import com.example.resumeAnalizer.repository.ResumeDataRepository;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "http://localhost:4200")
public class ResumeUploadController {
    

    @Autowired
    private ResumeDataRepository resumeDataRepository;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
        String content = new Tika().parseToString(file.getInputStream());

        ResumeData resume = new ResumeData();
        resume.setFileName(file.getOriginalFilename());
        resume.setParsedContent(content);
        resume.setUploadDate(LocalDateTime.now());

        resumeDataRepository.save(resume);

        return ResponseEntity.ok("Resume uploaded and saved successfully.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Failed to parse/save resume: " + e.getMessage());
    }
}
}
