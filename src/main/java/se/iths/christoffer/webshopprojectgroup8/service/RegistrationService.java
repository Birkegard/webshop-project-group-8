package se.iths.christoffer.webshopprojectgroup8.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;
import se.iths.christoffer.webshopprojectgroup8.repository.AppUserRepository;

@Service
public class RegistrationService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder encoder;

    public RegistrationService(AppUserRepository appUserRepository, PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.encoder = encoder;
    }

    public AppUser registration(AppUser appUser) {
        String encryptedPassword = encoder.encode(appUser.getPassword());
        appUser.setRole("USER");
        appUser.setPassword(encryptedPassword);
        return appUserRepository.save(appUser);
    }
}
