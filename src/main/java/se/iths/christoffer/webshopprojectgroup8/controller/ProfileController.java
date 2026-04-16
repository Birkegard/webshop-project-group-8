package se.iths.christoffer.webshopprojectgroup8.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.christoffer.webshopprojectgroup8.service.RegistrationService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final RegistrationService service;

    public ProfileController(RegistrationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "profile";
    }

    @PostMapping("/{id}")
    public String sendUserDetails(@PathVariable Long id) {
        service.sendUserInformation(id);
        return "redirect:/profile/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id, HttpServletRequest request) {
        try {
            service.deleteAppUser(id);
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
