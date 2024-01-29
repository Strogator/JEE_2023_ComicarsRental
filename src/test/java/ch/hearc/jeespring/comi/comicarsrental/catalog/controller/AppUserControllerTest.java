package ch.hearc.jeespring.comi.comicarsrental.catalog.controller;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AppUserControllerTest {

    @Mock
    private AppUserService userService;

    @InjectMocks
    private AppUserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        // Arrange
        List<AppUser> users = Arrays.asList(new AppUser(), new AppUser());
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<AppUser>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void registerUser_shouldReturnBadRequestWhenUsernameExists() {
        // Arrange
        AppUser existingUser = new AppUser();
        existingUser.setUsername("existingUser");
        when(userService.existsByUsername(existingUser.getUsername())).thenReturn(true);

        // Act
        ResponseEntity<String> response = userController.registerUser(existingUser);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Username already exists"));
        verify(userService, never()).saveUser(any());
    }

    @Test
    void registerUser_shouldReturnCreatedStatusWhenUserIsRegistered() {
        // Arrange
        AppUser newUser = new AppUser();
        newUser.setUsername("newUser");
        when(userService.existsByUsername(newUser.getUsername())).thenReturn(false);

        // Act
        ResponseEntity<String> response = userController.registerUser(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("User registered successfully"));
        verify(userService, times(1)).saveUser(newUser);
    }

    @Test
    void updateUser_shouldReturnNotFoundStatusWhenUserIdDoesNotExist() {
        // Arrange
        Long nonExistingUserId = 999L;
        AppUser updatedUser = new AppUser();
        updatedUser.setUserId(nonExistingUserId);
        when(userService.existsById(nonExistingUserId)).thenReturn(false);

        // Act
        ResponseEntity<String> response = userController.updateUser(nonExistingUserId, updatedUser);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("User not found"));
        verify(userService, never()).saveUser(any());
    }

    @Test
    void updateUser_shouldReturnOkStatusWhenUserIsUpdated() {
        // Arrange
        Long existingUserId = 1L;
        AppUser updatedUser = new AppUser();
        updatedUser.setUserId(existingUserId);
        when(userService.existsById(existingUserId)).thenReturn(true);

        // Act
        ResponseEntity<String> response = userController.updateUser(existingUserId, updatedUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("User updated successfully"));
        verify(userService, times(1)).saveUser(updatedUser);
    }
}
