package com.thelastcodebenders.bread_budget_backend.controllers;

import com.thelastcodebenders.bread_budget_backend.models.dto.AppResponse;
import com.thelastcodebenders.bread_budget_backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("users")
@RestController
public class UserController {

    private final UserService userService;

    @PutMapping("add-customer-id")
    public AppResponse addCustomerId(@RequestParam("customerId") String customerId) {
        return userService.addCustomerId(customerId);
    }
}
