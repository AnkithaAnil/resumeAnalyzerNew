package com.example.resumeAnalizer.dto;

public class MatchHistoryDto {
    private String resumeFileName;
    private String jdTitle;
    private double matchPercentage;

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
