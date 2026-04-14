package se.iths.christoffer.webshopprojectgroup8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Incorrect email")
    @Email
    @Column(unique = true, nullable = false)
    private String username; //email

    @NotBlank(message = "Incorrect password")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @AssertTrue(message = "You must consent to the storage of personal data")
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