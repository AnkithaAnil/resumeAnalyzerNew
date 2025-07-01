package com.example.resumeAnalizer.service;

import com.example.resumeAnalizer.model.MatchHistory;
import com.example.resumeAnalizer.repository.MatchHistoryRepository;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private MatchHistoryRepository matchHistoryRepository;

    private static final String UPLOAD_DIR = "uploads"; // Ensure this directory exists at runtime

    public byte[] generateEditedResume(Long userId) throws Exception {
        try {
            // 1. Fetch latest MatchHistory
            MatchHistory match = matchHistoryRepository.findTopByUserIdOrderByIdDesc(userId)
                    .orElseThrow(() -> new RuntimeException("No match history found for user"));

            String originalFileName = match.getResumeFileName();
            List<String> missingSkills = match.getMissingSkills();

            if (missingSkills == null || missingSkills.isEmpty()) {
                throw new RuntimeException("No missing skills found for this user");
            }

            // 2. Load original PDF
            File originalFile = new File(UPLOAD_DIR, originalFileName);
            System.out.println("🔍 Looking for file: " + originalFile.getAbsolutePath());

            if (!originalFile.exists()) {
                throw new FileNotFoundException("Resume file not found at: " + originalFile.getAbsolutePath());
            }

            PDDocument document = PDDocument.load(originalFile);

            // 3. Add a new page with missing skills
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Title
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Additional Skills:");
                contentStream.endText();

                // Skills
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                int y = 730;
                for (String skill : missingSkills) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, y);
                    contentStream.showText("- " + skill);
                    contentStream.endText();
                    y -= 20;
                    if (y < 100) break; // prevent page overflow
                }
            }

            // 4. Write to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();

            return baos.toByteArray();

        } catch (Exception e) {
            System.err.println("❗ Error in generateEditedResume:");
            e.printStackTrace();
            throw new RuntimeException("Resume generation failed", e);
        }
    }
}
