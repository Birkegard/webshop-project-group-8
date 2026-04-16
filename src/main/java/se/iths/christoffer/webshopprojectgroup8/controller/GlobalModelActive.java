package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;
import se.iths.christoffer.webshopprojectgroup8.service.RegistrationService;

import java.security.Principal;

@ControllerAdvice
public class GlobalModelActive {
    private final RegistrationService registrationService;

    public GlobalModelActive(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @ModelAttribute
    public void addGlobalAttribute(Model model, Principal principal) {
        if (principal != null) {
            AppUser fetchedUsername = registrationService.getUserByUsername(principal.getName());
            model.addAttribute("id", fetchedUsername.getId());

        }
    }
}
