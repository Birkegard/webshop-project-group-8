package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;
import se.iths.christoffer.webshopprojectgroup8.service.RegistrationService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    //byt ut appUserRepository och encoder mot en serivce istället.
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("register", new AppUser());
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute AppUser appUser) {
        registrationService.registration(appUser);
        return "redirect:/";

    }

}
