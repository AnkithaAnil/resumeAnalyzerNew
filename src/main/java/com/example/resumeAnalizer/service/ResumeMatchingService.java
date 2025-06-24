package com.example.resumeAnalizer.service;

import com.example.resumeAnalizer.dto.ResumeMatchResponseDto;
import com.example.resumeAnalizer.model.JobDescription;
import com.example.resumeAnalizer.model.ResumeData;
import com.example.resumeAnalizer.repository.JobDescriptionRepository;
import com.example.resumeAnalizer.repository.ResumeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeMatchingService {

    @Autowired
    private ResumeDataRepository resumeRepo;

    @Autowired
    private JobDescriptionRepository jdRepo;

    // ✅ Predefined list of known technical skills
    private static final Set<String> KNOWN_SKILLS = Set.of(
            "Java", "Spring", "Spring Boot", "Hibernate", "MySQL", "MongoDB",
            "Angular", "React", "Node.js", "JavaScript", "TypeScript", "HTML", "CSS",
            "Docker", "Kubernetes", "AWS", "Azure", "Git", "REST", "Microservices", "MCA", "Bachelor", "Master"
    );

    public List<ResumeMatchResponseDto> matchResumesWithJDs() {
        List<ResumeMatchResponseDto> result = new ArrayList<>();
        List<ResumeData> resumes = resumeRepo.findAll();
        List<JobDescription> jds = jdRepo.findAll();

        for (ResumeData resume : resumes) {
            for (JobDescription jd : jds) {
                Set<String> jdSkills = extractSkills(jd.getContent());
                Set<String> resumeSkills = extractSkills(resume.getParsedContent());

                Set<String> matchedSkills = new HashSet<>(resumeSkills);
                matchedSkills.retainAll(jdSkills);

                Set<String> missingSkills = new HashSet<>(jdSkills);
                missingSkills.removeAll(resumeSkills);

                double matchPercentage = jdSkills.isEmpty() ? 0.0 :
                        (matchedSkills.size() * 100.0) / jdSkills.size();

                // Build DTO
                ResumeMatchResponseDto dto = new ResumeMatchResponseDto();
                dto.setResumeFileName(resume.getFileName());
                dto.setJdTitle(jd.getTitle());
                dto.setMatchPercentage(matchPercentage);
                dto.setJdContent(jd.getContent());
                dto.setMatchedSkills(new ArrayList<>(matchedSkills));

                dto.setResumeSnippet(
                        resume.getParsedContent().substring(0, Math.min(300, resume.getParsedContent().length()))
                );
                dto.setMissingSkills(new ArrayList<>(missingSkills));

                result.add(dto);
            }
        }

        return result;
    }

    private Set<String> extractSkills(String text) {
    if (text == null) return Collections.emptySet();

    Set<String> words = Arrays.stream(text.toLowerCase().split("\\W+"))
            .collect(Collectors.toSet());

    return KNOWN_SKILLS.stream()
            .filter(skill -> words.contains(skill.toLowerCase()))
            .collect(Collectors.toSet());
}
}
