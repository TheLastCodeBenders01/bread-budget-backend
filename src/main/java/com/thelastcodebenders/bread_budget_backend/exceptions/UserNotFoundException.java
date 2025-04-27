package com.thelastcodebenders.bread_budget_backend.exceptions;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("USER");
    }
}
