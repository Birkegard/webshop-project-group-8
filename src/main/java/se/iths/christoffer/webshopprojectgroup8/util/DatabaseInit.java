package se.iths.christoffer.webshopprojectgroup8.util;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;
import se.iths.christoffer.webshopprojectgroup8.repository.AppUserRepository;

import java.util.List;

@Component
public class DatabaseInit {
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    public DatabaseInit(PasswordEncoder passwordEncoder,
                        AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }

    @PostConstruct
    public void createUsers() {
        AppUser admin = new AppUser();
        admin.setUsername("aslihan.taskin94@gmail.com");
        admin.setPassword(passwordEncoder.encode("ADMIN"));
        admin.setRole("ADMIN");
        admin.setConsent(true);

        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123"));
        user.setRole("USER");
        user.setConsent(true);

        List<AppUser> users = List.of(admin, user);
        users.stream()
                .filter(use -> appUserRepository.findByUsername(use.getUsername()).isEmpty())
                .forEach(appUserRepository::save);
    }
}
