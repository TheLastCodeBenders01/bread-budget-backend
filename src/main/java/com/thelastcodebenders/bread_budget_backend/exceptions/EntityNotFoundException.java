package com.thelastcodebenders.bread_budget_backend.exceptions;

public class EntityNotFoundException extends BreadBudgetException {

    EntityNotFoundException(String ENTITY) {
        super(ENTITY + " not found");
    }
}
