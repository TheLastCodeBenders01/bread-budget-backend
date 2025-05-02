package com.thelastcodebenders.bread_budget_backend.adapters;

import com.thelastcodebenders.bread_budget_backend.models.dto.TransactionSummaryResponse;
import com.thelastcodebenders.bread_budget_backend.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroqApiAdapter {

    @Value("${groq.api-secret}")
    private String groqApiKey;

    @Value("${groq.api-url}")
    private String groqApiUrl;

    private final RestTemplate restTemplate;

    public List<String> promptGroqForTransactionSummary(String prompt) {

        String preCommand = "This is a bank statement please can you generate insights on this bank statement" +
                "Start your response with <response>" +
                "Please i want three sections contained in tags" +
                " do not any form of '%' in your response please" +
                " please do not give amounts spent instead give the amounts in terms of percentages" +
                " you can reference amounts using words like 'a good amount' 'a small amount' and other relevant words" +
                " but for the values in the data section please put them in form of percentages" +
                " to get advice insights you can take inspiration from the book 'The richest man in Babylon'" +
                " and 'The psychology of Money book' also" +
                " give me a summary giving what i generally spend money inbetween the <summary>" +
                " tag and advice on how i can reduce my spending's between <advice> tag. Make the summary between 120 and 150 words" +
                " and make the advice between 120 and 150 words" +
                " Please do not return anything irrelevant to the question." +
                " I want you to give some data in this format also after the advice section inside a <data> tag" +
                " in the form {`transactionCategory1`:`transactionAmountPercentage`} without any backslashes(\\)" +
                " please follow my instructions for the data form strictly" +
                " please put only debits in the data section and make the categories as such" +
                " transfers to personal people, online payments and electronic transfer levy" +
//                "for debits prefix the transactionCategory with - for positive values prefix it with + sign" +
                "Here are the contents of the file:\n";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(groqApiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "meta-llama/llama-4-scout-17b-16e-instruct");
//        body.put("model", "llama-3.3-70b-versatile");
        body.put("messages", new Object[]{
                Map.of("role", "user","content", preCommand + prompt)
        });

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        String response = restTemplate.exchange(
                groqApiUrl,
                HttpMethod.POST,
                entity,
                TransactionSummaryResponse.class
        ).getBody().getChoices().get(0).getMessage().getContent().replaceAll("(?s)<think>.*?</think>", "").trim();


        String summary = Objects.requireNonNull(CommonUtils.getStringBetween(response, "<summary>", "</summary>"));
        String advice = Objects.requireNonNull(CommonUtils.getStringBetween(response, "<advice>", "</advice>"));

        String data = Objects.requireNonNull(CommonUtils.getStringBetween(response, "<data>", "</data>"))
                .replaceAll("[^a-zA-Z0-9{}\"`':,% ]", "").replace("`", "\"")
                .replace("'", "\"");

        log.info("Response is: {}", response);
        log.info("Data: {}", data);
        return List.of(summary, advice, data);
    }
}
