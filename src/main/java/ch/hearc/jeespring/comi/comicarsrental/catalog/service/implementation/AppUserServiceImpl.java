package ch.hearc.jeespring.comi.comicarsrental.catalog.service.implementation;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.repository.UserRepository;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing AppUser entities.
 * Author : Alessio Comi.
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(AppUser user) {
        userRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

}
