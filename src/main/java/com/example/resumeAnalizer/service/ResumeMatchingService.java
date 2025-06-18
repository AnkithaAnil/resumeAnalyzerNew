package com.example.resumeAnalizer.service;

import com.example.resumeAnalizer.dto.ResumeMatchResponseDto;
import com.example.resumeAnalizer.model.JobDescription;
import com.example.resumeAnalizer.model.ResumeData;
import com.example.resumeAnalizer.repository.JobDescriptionRepository;
import com.example.resumeAnalizer.repository.ResumeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeMatchingService {

    @Autowired
    private ResumeDataRepository resumeRepo;

    @Autowired
    private JobDescriptionRepository jdRepo;

    public List<ResumeMatchResponseDto> matchResumesWithJDs() {
        List<ResumeMatchResponseDto> result = new ArrayList<>();
        List<ResumeData> resumes = resumeRepo.findAll();
        List<JobDescription> jds = jdRepo.findAll();

        for (ResumeData resume : resumes) {
            for (JobDescription jd : jds) {
                double matchPercentage = calculateMatch(resume.getParsedContent(), jd.getContent());

                ResumeMatchResponseDto dto = new ResumeMatchResponseDto();
                dto.setResumeFileName(resume.getFileName());
                dto.setJdTitle(jd.getTitle());
                dto.setMatchPercentage(matchPercentage);
                dto.setJdContent(jd.getContent());
                dto.setResumeSnippet(
                    resume.getParsedContent().substring(0, Math.min(300, resume.getParsedContent().length()))
                );

                result.add(dto);
            }
        }

        return result;
    }

    // ✅ Improved accuracy with word-level comparison
    private double calculateMatch(String resumeText, String jdText) {
        if (resumeText == null || jdText == null) return 0.0;

        Set<String> resumeWords = new HashSet<>(Arrays.asList(resumeText.toLowerCase().split("\\W+")));
        String[] jdWords = jdText.toLowerCase().split("\\W+");

        if (jdWords.length == 0) return 0.0;

        long matchCount = Arrays.stream(jdWords)
                .filter(resumeWords::contains)
                .distinct()
                .count();

        return (matchCount * 100.0) / jdWords.length;
    }
}
