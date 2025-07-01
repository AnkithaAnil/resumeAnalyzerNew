package com.example.resumeAnalizer.dto;

import java.util.List;

public class MatchHistoryRequest {

    private String resumeFileName;
    private String jdTitle;
    private double matchPercentage;
    private String userEmail;
    private List<String> missingSkills;

public List<String> getMissingSkills() {
    return missingSkills;
}

public void setMissingSkills(List<String> missingSkills) {
    this.missingSkills = missingSkills;
}


    // Getters and Setters
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

