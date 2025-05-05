//package com.thelastcodebenders.bread_budget_backend.utils;
//
//import com.thelastcodebenders.bread_budget_backend.exceptions.BreadBudgetException;
//import com.thelastcodebenders.bread_budget_backend.models.User;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class UserUtil {
//
//    public static User getLoggedInUser() throws BreadBudgetException {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//
//            Object principal = authentication.getPrincipal();
//            return ((User) principal);
//        }
//        else {
//            throw new BreadBudgetException("Error getting logged in user");
//        }
//    }
//}
