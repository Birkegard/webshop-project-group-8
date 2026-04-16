package se.iths.christoffer.webshopprojectgroup8.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.christoffer.springmessenger.model.Email;
import se.iths.christoffer.springmessenger.service.MessageService;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;
import se.iths.christoffer.webshopprojectgroup8.repository.AppUserRepository;

@Service
public class RegistrationService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder encoder;
    private final MessageService messageService;

    public RegistrationService(AppUserRepository appUserRepository, PasswordEncoder encoder, MessageService messageService) {
        this.appUserRepository = appUserRepository;
        this.encoder = encoder;
        this.messageService = messageService;
    }

    public AppUser registration(AppUser appUser) {
        String encryptedPassword = encoder.encode(appUser.getPassword());
        appUser.setRole("USER");
        appUser.setPassword(encryptedPassword);
        return appUserRepository.save(appUser);
    }

    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Användare med id: " + id + " finns inte."));
    }

    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Användaren finns inte."));
    }

    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public void sendUserInformation(Long id) {
        AppUser appUser = getUserById(id);

        Email email = new Email();
        email.setRecipient(appUser.getUsername());
        email.setMessage("Username: " + appUser.getUsername() + " Role: " + appUser.getRole());
        email.setSubject("Your user-details");
        messageService.send(email);
    }
}
