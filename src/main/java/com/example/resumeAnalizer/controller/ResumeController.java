package com.example.resumeAnalizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import com.example.resumeAnalizer.service.ResumeService;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "http://localhost:4200")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;
@GetMapping("/download-edited")
public ResponseEntity<byte[]> downloadEditedResume(@RequestParam Long userId) {
    try {
        byte[] editedPdf = resumeService.generateEditedResume(userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Edited_Resume.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(editedPdf);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }
}
}

