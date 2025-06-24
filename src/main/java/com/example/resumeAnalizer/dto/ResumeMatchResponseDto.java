package com.example.resumeAnalizer.dto;

import java.util.List;

public class ResumeMatchResponseDto {

    private String resumeFileName;
    private String jdTitle;
    private double matchPercentage;
    private String jdContent;
    private String resumeSnippet;
    private List<String> missingSkills; // ✅ New field
    private List<String> matchedSkills;

// Getter and Setter
public List<String> getMatchedSkills() {
    return matchedSkills;
}

public void setMatchedSkills(List<String> matchedSkills) {
    this.matchedSkills = matchedSkills;
}


    // Getter and Setter for resumeFileName
    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    // Getter and Setter for jdTitle
    public String getJdTitle() {
        return jdTitle;
    }

    public void setJdTitle(String jdTitle) {
        this.jdTitle = jdTitle;
    }

    // Getter and Setter for matchPercentage
    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    // Getter and Setter for jdContent
    public String getJdContent() {
        return jdContent;
    }

    public void setJdContent(String jdContent) {
        this.jdContent = jdContent;
    }

    // Getter and Setter for resumeSnippet
    public String getResumeSnippet() {
        return resumeSnippet;
    }

    public void setResumeSnippet(String resumeSnippet) {
        this.resumeSnippet = resumeSnippet;
    }

    // ✅ Getter and Setter for missingSkills
    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
