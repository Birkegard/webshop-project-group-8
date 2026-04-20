package se.iths.christoffer.webshopprojectgroup8;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.iths.christoffer.webshopprojectgroup8.service.MailService;

@SpringBootTest
@ActiveProfiles("test")
class WebshopProjectGroup8ApplicationTests {

    @MockBean
    private MailService mailService;

    @Test
    void contextLoads() {
    }

}
