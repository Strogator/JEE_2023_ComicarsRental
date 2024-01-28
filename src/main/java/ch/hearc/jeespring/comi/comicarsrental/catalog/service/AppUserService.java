package ch.hearc.jeespring.comi.comicarsrental.catalog.service;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;

import java.util.List;

/**
 * Service interface for managing AppUser entities.
 * Author : Alessio Comi.
 */
public interface AppUserService {

    /**
     * Checks if a user with the given ID exists.
     *
     * @param userId The ID of the user.
     * @return True if a user with the ID exists, false otherwise.
     */
    boolean existsById(Long userId);

    /**
     * Checks if a user with the given username exists.
     *
     * @param username The username to check.
     * @return True if a user with the username exists, false otherwise.
     */
    boolean existsByUsername(String username);

    /**
     * Saves a user entity to the database.
     *
     * @param user The user to save.
     */
    void saveUser(AppUser user);

    /**
     * Retrieves a list of all users.
     *
     * @return List of all users in the database.
     */
    List<AppUser> getAllUsers();
}
