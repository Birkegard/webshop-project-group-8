package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;
import se.iths.christoffer.webshopprojectgroup8.repository.AppUserRepository;

@Controller
public class RegistrationController {
    //byt ut appUserRepository och encoder mot en serivce istället.
    private final AppUserRepository appUserRepository;

    private final PasswordEncoder encoder;

    public RegistrationController(AppUserRepository appUserRepository, PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("register", new AppUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute AppUser appUser) {
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        return "redirect:/";

    }

}
