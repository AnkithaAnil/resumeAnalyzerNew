package com.example.resumeAnalizer.controller;

import com.example.resumeAnalizer.model.JobDescription;
import com.example.resumeAnalizer.repository.JobDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/jd")
@CrossOrigin(origins = "http://localhost:4200")
public class JDController {

    @Autowired
    private JobDescriptionRepository jdRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadJD(@RequestBody JobDescription jd) {
        jd.setUploadDate(LocalDateTime.now());
        jdRepository.save(jd);
        return ResponseEntity.ok("JD saved successfully.");
    }
}
