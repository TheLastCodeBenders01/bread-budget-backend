package com.thelastcodebenders.bread_budget_backend.services;

import com.thelastcodebenders.bread_budget_backend.exceptions.UserNotFoundException;
import com.thelastcodebenders.bread_budget_backend.models.User;
import com.thelastcodebenders.bread_budget_backend.models.dto.LoginResponse;
import com.thelastcodebenders.bread_budget_backend.models.dto.UserRequest;
import com.thelastcodebenders.bread_budget_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void signUp(UserRequest userRequest) {

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();

        userRepository.save(user);
    }

    public LoginResponse oauthLogin(String email) throws UserNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .userId(user.getUserId())
                .build();
    }
}
