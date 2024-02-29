package com.uktamjon.sodikov.controllers;

import com.uktamjon.sodikov.config.UserDetailsService;
import com.uktamjon.sodikov.dtos.CreateAuthUserDTO;
import com.uktamjon.sodikov.dtos.GetTokenDTO;
import com.uktamjon.sodikov.response.RefreshTokenRequest;
import com.uktamjon.sodikov.response.TokenRequest;
import com.uktamjon.sodikov.response.TokenResponse;
import com.uktamjon.sodikov.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final AuthService authService;


    @GetMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@Valid TokenRequest tokenRequest) {
        log.info("Getting token for user: {}", tokenRequest.getUsername());
        return ResponseEntity.ok(userDetailsService.generateToken(tokenRequest));
    }


    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid RefreshTokenRequest refreshTokenRequest) {
        log.info("Getting refresh token: {}", refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(userDetailsService.refreshToken(refreshTokenRequest));
    }


    @GetMapping("/login")
    public ResponseEntity<GetTokenDTO> login(CreateAuthUserDTO request) {
        log.info("Logging in with user: {}", request.getUsername());
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        log.info("Logging out");
        return ResponseEntity.noContent().build();
    }

}