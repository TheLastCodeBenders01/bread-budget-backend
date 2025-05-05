//package com.thelastcodebenders.bread_budget_backend.services;
//
//import com.thelastcodebenders.bread_budget_backend.exceptions.UserNotFoundException;
//import com.thelastcodebenders.bread_budget_backend.models.User;
//import com.thelastcodebenders.bread_budget_backend.models.dto.AppResponse;
//import com.thelastcodebenders.bread_budget_backend.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@RequiredArgsConstructor
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public User findByUserId(UUID userId) throws UserNotFoundException {
//        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
//    }
//
//    public boolean existsByEmail(String email) {
//        return userRepository.existsByEmail(email);
//    }
//
//    public User findUserByEmail(String email) throws UserNotFoundException {
//        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
//    }
//
//    public AppResponse addCustomerId(String customerId) {
////        User user = UserUtil.getLoggedInUser();
////
////        user.setCustomerId(customerId);
////        saveUser(user);
////
////        return AppResponse.builder()
////                .message("Bank Id successfully saved")
////                .status(HttpStatus.OK)
////                .build();
//        return null;
//    }
//
//    public void saveUser(User user) {
//        userRepository.save(user);
//    }
//}
