package se.iths.christoffer.webshopprojectgroup8.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.christoffer.webshopprojectgroup8.model.AppUser;
import se.iths.christoffer.webshopprojectgroup8.repository.AppUserRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void registration_shouldSaveUser() {
        AppUser user = new AppUser();
        user.setPassword("123");

        when(passwordEncoder.encode("123"))
                .thenReturn("encoded");

        registrationService.registration(user);

        verify(appUserRepository).save(user);
    }

    @Test
    void deleteUser_shouldCallRepository() {
        registrationService.deleteAppUser(1L);

        verify(appUserRepository).deleteById(1L);
    }
}