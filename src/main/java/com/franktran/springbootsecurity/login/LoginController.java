package com.franktran.springbootsecurity.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/show-login")
    public String showLogin() {
        return "login";
    }
}
