package ch.hearc.jeespring.comi.comicarsrental.catalog.model;

import jakarta.persistence.*;

/**
 * Entity class representing an application user.
 * Author : Alessio Comi.
 */
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String email;

    // Constructors
    public AppUser() {
    }

    public AppUser(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getter and Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {this.username = username;}

}
