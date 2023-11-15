package com.example.back;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.back.controller.UserController;
import com.example.back.entity.User;
import com.example.back.service.UserService;

public class UserControllerTest {
    
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testGetUser() {
        int userId = 1;
        User expectedUser = mock(User.class);
        when(userService.getUser(userId)).thenReturn(expectedUser);

        User actualUser = userController.getUser(userId);

        assertEquals(expectedUser, actualUser);
        verify(userService).getUser(userId);
    }

}
