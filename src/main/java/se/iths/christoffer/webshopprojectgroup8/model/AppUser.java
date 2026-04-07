package se.iths.christoffer.webshopprojectgroup8.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; //ska vara email

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean consent;

    public AppUser() {
    }

    public AppUser(String username, String password,
                   String role, boolean consent) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.consent = consent;
    }
}