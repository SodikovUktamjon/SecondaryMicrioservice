package com.uktamjon.sodikov.repositories;

import com.uktamjon.sodikov.domains.User;
import com.uktamjon.sodikov.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindAllByUsernameContains() {
        String username = "test";
        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findAllByUsernameContains(username)).thenReturn(expectedUsers);
        List<User> actualUsers = userService.findByCriteria(username);
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).findAllByUsernameContains(username);
    }

    @Test
    public void testFindAll() {
        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUsers();
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindByUsername() {
        String username = "test";
        User expectedUser = new User();
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);
        User actualUser = userService.getUserByUsername(username);
        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findByUsername(username);
    }



    @Test
    public void testDeleteByUsername() {
        String username = "test";
        when(userRepository.findByUsername(Mockito.anyString())).thenReturn(new User());
        userService.deleteByUsername(username);
        verify(userRepository, times(1)).deleteByUsername(username);
    }
}
