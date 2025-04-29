package com.thelastcodebenders.bread_budget_backend.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionSummaryResponse {

    private List<GroqChoiceDto> choices;
    private Usage usage;

    @Data
    public static class GroqChoiceDto {
        private GroqMessageDto message;
    }

    @Data
    public static class GroqMessageDto {
        private String role;
        private String content;
    }

    @Data
    public static class Usage {
        private int total_tokens;
    }
}
