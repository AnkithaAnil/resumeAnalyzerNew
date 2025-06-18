package com.example.resumeAnalizer.repository;

import com.example.resumeAnalizer.model.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
}
