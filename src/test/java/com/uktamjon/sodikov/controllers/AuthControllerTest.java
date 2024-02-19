package com.uktamjon.sodikov.controllers;

import com.uktamjon.sodikov.config.UserDetailsService;
import com.uktamjon.sodikov.dtos.CreateAuthUserDTO;
import com.uktamjon.sodikov.dtos.GetTokenDTO;
import com.uktamjon.sodikov.response.RefreshTokenRequest;
import com.uktamjon.sodikov.response.TokenRequest;
import com.uktamjon.sodikov.response.TokenResponse;
import com.uktamjon.sodikov.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthService authService;


    @InjectMocks
    private AuthController authController;

    @Test
    void testGetToken() {
        TokenRequest tokenRequest = new TokenRequest();
        when(userDetailsService.generateToken(tokenRequest)).thenReturn(new TokenResponse());

        ResponseEntity<TokenResponse> responseEntity = authController.getToken(tokenRequest);

        verify(userDetailsService).generateToken(tokenRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testRefresh() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        when(userDetailsService.refreshToken(refreshTokenRequest)).thenReturn(new TokenResponse());

        ResponseEntity<TokenResponse> responseEntity = authController.refresh(refreshTokenRequest);

        verify(userDetailsService).refreshToken(refreshTokenRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testLogin() {
        CreateAuthUserDTO createAuthUserDTO = new CreateAuthUserDTO();
        when(authService.login(createAuthUserDTO)).thenReturn(new GetTokenDTO());

        ResponseEntity<GetTokenDTO> responseEntity = authController.login(createAuthUserDTO);

        verify(authService).login(createAuthUserDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testLogout() {
        ResponseEntity<Void> responseEntity = authController.logout();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
