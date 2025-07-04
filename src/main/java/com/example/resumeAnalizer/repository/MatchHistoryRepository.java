package com.example.resumeAnalizer.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumeAnalizer.model.MatchHistory;

public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {
    List<MatchHistory> findByUserEmail(String email);
    List<MatchHistory> findByUserId(Long userId);
     Optional<MatchHistory> findTopByUserIdOrderByIdDesc(Long userId);
}
