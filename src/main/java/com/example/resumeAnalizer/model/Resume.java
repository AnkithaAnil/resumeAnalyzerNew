package com.example.resumeAnalizer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath; // location of resume file on disk
    private LocalDateTime uploadedAt;

    @ManyToOne
    private User user;

    // Getters and Setters
    public Long getId() { return id; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
