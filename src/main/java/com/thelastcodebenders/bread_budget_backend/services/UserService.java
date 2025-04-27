package com.thelastcodebenders.bread_budget_backend.services;

import com.thelastcodebenders.bread_budget_backend.exceptions.UserNotFoundException;
import com.thelastcodebenders.bread_budget_backend.models.User;
import com.thelastcodebenders.bread_budget_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByUserId(UUID userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
