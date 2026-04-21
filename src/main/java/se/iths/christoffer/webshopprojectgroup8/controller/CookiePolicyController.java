package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookiepolicy")
public class CookiePolicyController {

    @GetMapping
    public String cookiePolicy() {
        return "cookiepolicy";
    }
}
