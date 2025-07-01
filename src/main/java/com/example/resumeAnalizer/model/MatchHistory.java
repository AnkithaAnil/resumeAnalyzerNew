package com.example.resumeAnalizer.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class MatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeFileName;
    private String jdTitle;
    private double matchPercentage;

    @ManyToOne
    private User user;

    @ElementCollection
    private List<String> missingSkills;

    // ✅ Default constructor required by JPA
    public MatchHistory() {}

    // ✅ Constructor with missingSkills
    public MatchHistory(String resumeFileName, String jdTitle, double matchPercentage, User user, List<String> missingSkills) {
        this.resumeFileName = resumeFileName;
        this.jdTitle = jdTitle;
        this.matchPercentage = matchPercentage;
        this.user = user;
        this.missingSkills = missingSkills;
    }

    // ✅ Constructor without missingSkills (optional use)
    public MatchHistory(String resumeFileName, String jdTitle, double matchPercentage, User user) {
        this.resumeFileName = resumeFileName;
        this.jdTitle = jdTitle;
        this.matchPercentage = matchPercentage;
        this.user = user;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public String getJdTitle() {
        return jdTitle;
    }

    public void setJdTitle(String jdTitle) {
        this.jdTitle = jdTitle;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
