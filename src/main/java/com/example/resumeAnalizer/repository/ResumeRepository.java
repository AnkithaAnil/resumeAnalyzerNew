package com.example.resumeAnalizer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumeAnalizer.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findTopByUserIdOrderByIdDesc(Long userId);
}

