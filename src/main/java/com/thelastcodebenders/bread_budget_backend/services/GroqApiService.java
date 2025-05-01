package com.thelastcodebenders.bread_budget_backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thelastcodebenders.bread_budget_backend.adapters.GroqApiAdapter;
import com.thelastcodebenders.bread_budget_backend.models.dto.SummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroqApiService {

    private final GroqApiAdapter groqApiAdapter;
    private final ObjectMapper objectMapper;

    public SummaryResponse generateSummary(MultipartFile file) {
//        public SummaryResponse generateSummary(String fileContents) {

        while (true) {
            try {

                InputStream inputStream = file.getInputStream();
                PDDocument document = PDDocument.load(inputStream);

                PDFTextStripper stripper = new PDFTextStripper();
                String fileContents = stripper.getText(document);
                log.info("Content is: {}", fileContents);

                List<String> response = groqApiAdapter.promptGroqForTransactionSummary(fileContents);

                inputStream.close();

                return SummaryResponse.builder()
                        .data(Map.of("summary", response.get(0),
                                "advice", response.get(1),
                                "data", objectMapper.readValue(response.get(2), Map.class))).build();
            } catch (IOException e) {
                log.info("Error: trying again");
            }
        }
    }
}
