package com.example.resumeAnalizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resumeAnalizer.model.MatchHistory;
import com.example.resumeAnalizer.model.User;
import com.example.resumeAnalizer.repository.MatchHistoryRepository;
import com.example.resumeAnalizer.repository.UserRepository;

@Service
public class MatchHistoryService {
    @Autowired
private MatchHistoryRepository historyRepository;

@Autowired
private UserRepository userRepository;

// Example usage
public MatchHistory saveMatchHistory(String resumeFileName, String jdTitle, double matchPercentage, String userEmail) {
    User user = userRepository.findByEmailIgnoreCase(userEmail)
                  .orElseThrow(() -> new RuntimeException("User not found"));

    MatchHistory history = new MatchHistory();
    history.setResumeFileName(resumeFileName);
    history.setJdTitle(jdTitle);
    history.setMatchPercentage(matchPercentage);
    history.setUser(user);

    return historyRepository.save(history);
}


}
