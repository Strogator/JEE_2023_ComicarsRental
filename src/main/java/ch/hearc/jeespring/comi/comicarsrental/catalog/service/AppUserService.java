package ch.hearc.jeespring.comi.comicarsrental.catalog.service;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;

public interface AppUserService {

    public boolean existsByUsername(String username);

    public void saveUser(AppUser user) ;

}
