//package com.thelastcodebenders.bread_budget_backend.config;
//
//import com.thelastcodebenders.bread_budget_backend.models.dto.LoginResponse;
//import com.thelastcodebenders.bread_budget_backend.models.dto.UserRequest;
//import com.thelastcodebenders.bread_budget_backend.services.AuthenticationService;
//import com.thelastcodebenders.bread_budget_backend.services.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private final AuthenticationService authenticationService;
//    private final UserService userService;
//
//    @SneakyThrows
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) {
//        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
//        OAuth2User oAuth2User = authToken.getPrincipal();
//
//        String email = oAuth2User.getAttribute("email");
//        String firstName = oAuth2User.getAttribute("given_name").toString().substring(0, 1).toUpperCase() + oAuth2User.getAttribute("given_name").toString().substring(1);
//        String lastName = oAuth2User.getAttribute("family_name").toString().substring(0, 1).toUpperCase() + oAuth2User.getAttribute("family_name").toString().substring(1);
//
//        UserRequest userRequest = UserRequest.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .email(email)
//                .password(UUID.randomUUID().toString())
//                .build();
//
//        if (!userService.existsByEmail(email)) {
//            authenticationService.signUp(userRequest);
//        }
//
//        LoginResponse loginResponse = authenticationService.oauthLogin(email);
//
//        response.setStatus(HttpServletResponse.SC_OK); // Set status code
//        response.setContentType("application/json"); // Set content type
//        response.getWriter().write("{\"token\":\"" + loginResponse.getToken() + "\"}"); // Write response body
//        response.getWriter().flush();
//    }
//}
