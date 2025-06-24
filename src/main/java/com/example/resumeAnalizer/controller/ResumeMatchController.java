package com.example.resumeAnalizer.controller;

import com.example.resumeAnalizer.dto.ResumeMatchResponseDto;
import com.example.resumeAnalizer.service.ResumeMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
@CrossOrigin(origins = "http://localhost:4200")
public class ResumeMatchController {

    @Autowired
    private ResumeMatchingService matchingService;

    @GetMapping("/results")
    public ResponseEntity<List<ResumeMatchResponseDto>> getMatchResults() {
        // ✅ This will now include missingSkills if your service layer populates it
        List<ResumeMatchResponseDto> results = matchingService.matchResumesWithJDs();
        return ResponseEntity.ok(results);
    }
}
