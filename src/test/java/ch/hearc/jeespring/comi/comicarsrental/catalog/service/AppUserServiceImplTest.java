package ch.hearc.jeespring.comi.comicarsrental.catalog.service;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.repository.UserRepository;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.implementation.AppUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AppUserService userService = new AppUserServiceImpl(userRepository);

    @Test
    public void existsById_shouldReturnTrueWhenUserExists() {
        // Arrange
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        boolean result = userService.existsById(userId);

        // Assert
        assertTrue(result);
    }

    @Test
    public void existsByUsername_shouldReturnTrueWhenUsernameExists() {
        // Arrange
        String username = "testUser";
        when(userRepository.existsByUsername(username)).thenReturn(true);

        // Act
        boolean result = userService.existsByUsername(username);

        // Assert
        assertTrue(result);
    }

    @Test
    public void saveUser_shouldCallSaveMethodInRepository() {
        // Arrange
        AppUser user = new AppUser();

        // Act
        userService.saveUser(user);

        // Assert
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getAllUsers_shouldReturnListOfUsers() {
        // Arrange
        List<AppUser> users = Arrays.asList(new AppUser(), new AppUser());
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<AppUser> result = userService.getAllUsers();

        // Assert
        assertEquals(users, result);
    }
}
