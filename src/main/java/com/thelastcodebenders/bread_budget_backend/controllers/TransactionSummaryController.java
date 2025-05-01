package com.thelastcodebenders.bread_budget_backend.controllers;

import com.thelastcodebenders.bread_budget_backend.models.dto.SummaryResponse;
import com.thelastcodebenders.bread_budget_backend.models.dto.TransactionSummaryRequest;
import com.thelastcodebenders.bread_budget_backend.services.GroqApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("transaction-summary")
@RestController
public class TransactionSummaryController {

    private final GroqApiService groqApiService;

    @PostMapping(consumes = "multipart/form-data")
    public SummaryResponse getLastWeekSummary(@RequestParam("file") MultipartFile file) {
//    public SummaryResponse getLastWeekSummary(@RequestBody TransactionSummaryRequest transactionSummaryRequest) {
        return groqApiService.generateSummary(file);
    }
}
