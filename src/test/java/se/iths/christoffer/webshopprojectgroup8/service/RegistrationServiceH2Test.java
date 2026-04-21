package se.iths.christoffer.webshopprojectgroup8.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class RegistrationServiceH2Test {

    @MockitoBean
    private JavaMailSender javaMailSender;

    @Autowired
    private RegistrationService registrationService;

    @Test
    void registration_shouldSaveUserToDatabase() {
        AppUser user = new AppUser();
        user.setUsername("test@test.com");
        user.setPassword("123");
        user.setConsent(true);
        user.setRole("USER");

        AppUser saved = registrationService.registration(user);

        assertNotNull(saved.getId());
        assertEquals("test@test.com", saved.getUsername());
    }
}