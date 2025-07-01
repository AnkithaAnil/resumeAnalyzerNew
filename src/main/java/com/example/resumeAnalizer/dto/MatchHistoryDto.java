package com.example.resumeAnalizer.dto;

import java.util.List;

public class MatchHistoryDto {
    private String resumeFileName;
    private String jdTitle;
    private double matchPercentage;
    private List<String> missingSkills;

public List<String> getMissingSkills() {
    return missingSkills;
}

public void setMissingSkills(List<String> missingSkills) {
    this.missingSkills = missingSkills;
}


    public MatchHistoryDto(String resumeFileName, String jdTitle, double matchPercentage) {
        this.resumeFileName = resumeFileName;
        this.jdTitle = jdTitle;
        this.matchPercentage = matchPercentage;
    }

    // Getters
    public String getResumeFileName() { return resumeFileName; }
    public String getJdTitle() { return jdTitle; }
    public double getMatchPercentage() { return matchPercentage; }
}
