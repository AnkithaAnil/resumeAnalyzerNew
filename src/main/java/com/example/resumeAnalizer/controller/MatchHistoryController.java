package com.example.resumeAnalizer.controller;

import com.example.resumeAnalizer.dto.MatchHistoryDto;
import com.example.resumeAnalizer.dto.MatchHistoryRequest;
import com.example.resumeAnalizer.model.MatchHistory;
import com.example.resumeAnalizer.repository.MatchHistoryRepository;
import com.example.resumeAnalizer.service.MatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:4200")
public class MatchHistoryController {

    @Autowired
    private MatchHistoryRepository matchHistoryRepository;

    @Autowired
    private MatchHistoryService matchHistoryService;

    // ✅ GET history by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<List<MatchHistoryDto>> getMatchHistory(@PathVariable Long userId) {
        List<MatchHistory> history = matchHistoryRepository.findByUserId(userId);

        List<MatchHistoryDto> historyDtoList = history.stream()
                .map(record -> new MatchHistoryDto(
                        record.getResumeFileName(),
                        record.getJdTitle(),
                        record.getMatchPercentage()
                ))
                .toList();

        return ResponseEntity.ok(historyDtoList);
    }

    // ✅ SAVE history using request body (resume + jd + match + email)
    @PostMapping("/save")
    public ResponseEntity<MatchHistory> saveHistory(@RequestBody MatchHistoryRequest request) {
        MatchHistory saved = matchHistoryService.saveMatchHistory(
                request.getResumeFileName(),
                request.getJdTitle(),
                request.getMatchPercentage(),
                request.getUserEmail()
        );
        return ResponseEntity.ok(saved);
    }
}
