package com.uktamjon.sodikov.services;

import com.uktamjon.sodikov.config.JwtUtils;
import com.uktamjon.sodikov.domains.User;
import com.uktamjon.sodikov.dtos.CreateAuthUserDTO;
import com.uktamjon.sodikov.dtos.GetTokenDTO;
import com.uktamjon.sodikov.repositories.UserRepository;
import com.uktamjon.sodikov.response.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private PasswordGeneratorService passwordGeneratorService;

    @Mock
    private UserRepository authUserRepository;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;


    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        AuthService authService = new AuthService(passwordGeneratorService, authUserRepository, jwtUtils, authenticationManager);
    }

    @Test
    void login_Success() {
        CreateAuthUserDTO dto = CreateAuthUserDTO.builder()
                .username("username")
                .password("password")
                .build();

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(authUserRepository.findByUsername(Mockito.any())).thenReturn(User.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .build());

        when(passwordGeneratorService.checkPassword(any(), any())).thenReturn(true);
        when(jwtUtils.generateToken(any())).thenReturn(TokenResponse.builder().accessToken("access-token").build());

        GetTokenDTO result = authService.login(dto);

        assertNotNull(result);
        assertEquals("access-token", result.getToken());
    }

    @Test
    void login_Failure() {
        CreateAuthUserDTO dto = CreateAuthUserDTO.builder()
                .username("username")
                .password("password")
                .build();

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(authUserRepository.findByUsername(dto.getUsername())).thenReturn(User.builder().build());
        when(passwordGeneratorService.checkPassword(any(), any())).thenReturn(false);
        GetTokenDTO result = authService.login(dto);

        assertNull(result);
    }

//    @Test
//    void login_UserBlocked() {
//        CreateAuthUserDTO dto = CreateAuthUserDTO.builder()
//                .username("blockedUser")
//                .password("password")
//                .build();
//
//
//        GetTokenDTO result = authService.login(dto);
//
//        assertNull(result);
//    }
}
