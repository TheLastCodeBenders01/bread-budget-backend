package com.thelastcodebenders.bread_budget_backend.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SummaryResponse {
    private Map<String, Object> data;
}
