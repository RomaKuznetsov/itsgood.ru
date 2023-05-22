package com.itsgood.ru.security.controller;


import com.itsgood.ru.security.configuration.JWTConfiguration;
import com.itsgood.ru.security.dto.AuthRequest;
import com.itsgood.ru.security.dto.AuthResponse;
import com.itsgood.ru.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider provider;
    private final UserDetailsService userProvider;
    private final JWTConfiguration jwtConfiguration;
    private final PasswordEncoder passwordEncoder;
    //ok
    @PostMapping(value = "/authentication", consumes = {"application/xml", "application/json"})
    public ResponseEntity<AuthResponse> loginUser(@AuthenticationPrincipal(expression = "request") @RequestBody AuthRequest request) {
        /*Check login and password*/
        UserDetails userDetails = userProvider.loadUserByUsername(request.getLogin());
        if (passwordEncoder.matches(userDetails.getPassword(),request.getPassword() +
                jwtConfiguration.getServerPasswordSalt())) {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getLogin(), userDetails.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
        /*Generate token with answer to user*/
        return ResponseEntity.ok(
                AuthResponse.builder().login(request.getLogin())
                        .token(provider.generateToken(userDetails)).build());
    }
}
