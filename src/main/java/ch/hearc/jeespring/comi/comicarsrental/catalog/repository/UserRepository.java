package ch.hearc.jeespring.comi.comicarsrental.catalog.repository;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByUsernameOrEmail(String username, String email);

    Optional<AppUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

