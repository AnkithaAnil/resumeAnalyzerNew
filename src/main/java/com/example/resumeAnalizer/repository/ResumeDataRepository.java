package com.example.resumeAnalizer.repository;

import com.example.resumeAnalizer.model.ResumeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDataRepository extends JpaRepository<ResumeData, Long> {

}
