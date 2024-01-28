package ch.hearc.jeespring.comi.comicarsrental.catalog.service.implementation;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.repository.UserRepository;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements AppUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(AppUser user) {
        userRepository.save(user);
    }

}
