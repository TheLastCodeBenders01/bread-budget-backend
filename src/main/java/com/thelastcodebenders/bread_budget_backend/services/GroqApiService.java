package com.thelastcodebenders.bread_budget_backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thelastcodebenders.bread_budget_backend.adapters.GroqApiAdapter;
import com.thelastcodebenders.bread_budget_backend.models.dto.SummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroqApiService {

    private final GroqApiAdapter groqApiAdapter;
    private final ObjectMapper objectMapper;

//    public SummaryResponse generateSummary(MultipartFile file) {
        public SummaryResponse generateSummary(String fileContents) {

        while (true) {
            try {
                log.info("Content is: {}", fileContents);

                List<String> response = groqApiAdapter.promptGroqForTransactionSummary(fileContents);

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
