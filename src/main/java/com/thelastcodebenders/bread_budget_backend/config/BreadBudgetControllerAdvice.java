package com.thelastcodebenders.bread_budget_backend.config;

import com.thelastcodebenders.bread_budget_backend.models.dto.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BreadBudgetControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppResponse> handleGenericException(Exception ex, WebRequest request) {

        System.out.println(ex.getMessage());
        AppResponse message = AppResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
