package ch.hearc.jeespring.comi.comicarsrental.catalog.repository;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing AppUser entities in the database.
 * Author : Alessio Comi.
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Checks if a user with the given username exists.
     *
     * @param username The username to check.
     * @return True if a user with the username exists, false otherwise.
     */
    Boolean existsByUsername(String username);
}
