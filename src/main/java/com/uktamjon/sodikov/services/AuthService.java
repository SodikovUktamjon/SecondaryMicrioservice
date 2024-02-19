package com.uktamjon.sodikov.services;

import com.uktamjon.sodikov.domains.User;
import com.uktamjon.sodikov.repositories.UserRepository;
import com.uktamjon.sodikov.config.JwtUtils;
import com.uktamjon.sodikov.dtos.CreateAuthUserDTO;
import com.uktamjon.sodikov.dtos.GetTokenDTO;
import com.uktamjon.sodikov.response.TokenResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final PasswordGeneratorService passwordGeneratorService;
    private final UserRepository authUserRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

   @CircuitBreaker(name = "authService", fallbackMethod = "loginFallback")
    public GetTokenDTO login(CreateAuthUserDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        User user = authUserRepository.findByUsername(dto.getUsername());
        if(passwordGeneratorService.checkPassword(dto.getPassword(), user.getPassword())) {
            TokenResponse tokenResponse = jwtUtils.generateToken(dto.getUsername());
            return GetTokenDTO.builder()
                    .token(tokenResponse.getAccessToken())
                    .build();
        }
        return null;

    }
    public GetTokenDTO loginFallback(CreateAuthUserDTO dto, Exception e) {
        log.trace("Exception while logging in: {}", e.getMessage());
        return GetTokenDTO.builder()
                .token("fallback-token")
                .build();
    }

}