package ch.hearc.jeespring.comi.comicarsrental.catalog.controller;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling user-related operations.
 * Author : Alessio Comi.
 */
@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService userService;

    @Autowired
    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing the list of all users.
     */
    @GetMapping("/")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Registers a new user and returns an appropriate response.
     *
     * @param user The user details.
     * @return ResponseEntity indicating the success or failure of the user registration.
     */
    @PostMapping("/")
    public ResponseEntity<String> registerUser(@RequestBody AppUser user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    /**
     * Updates an existing user based on the given user ID.
     *
     * @param userId      The ID of the user to be updated.
     * @param updatedUser The updated user details.
     * @return ResponseEntity indicating the success or failure of the user update.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody AppUser updatedUser) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        updatedUser.setUserId(userId);
        userService.saveUser(updatedUser);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }
}
