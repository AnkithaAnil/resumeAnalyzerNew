package com.example.resumeAnalizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resumeAnalizer.model.MatchHistory;
import com.example.resumeAnalizer.model.User;
import com.example.resumeAnalizer.repository.MatchHistoryRepository;
import com.example.resumeAnalizer.repository.UserRepository;

import java.util.List;

@Service
public class MatchHistoryService {

    @Autowired
    private MatchHistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

   public MatchHistory saveMatchHistory(String resumeFileName, String jdTitle, double matchPercentage, List<String> missingSkills, String userEmail) {
    User user = userRepository.findByEmailIgnoreCase(userEmail)
            .orElseThrow(() -> new RuntimeException("User not found"));

    MatchHistory history = new MatchHistory();
    history.setResumeFileName(resumeFileName);
    history.setJdTitle(jdTitle);
    history.setMatchPercentage(matchPercentage);
    history.setMissingSkills(missingSkills); // ✅ Add this
    history.setUser(user);
    System.out.println("Saving missing skills: " + missingSkills);


    return historyRepository.save(history);
}

}
