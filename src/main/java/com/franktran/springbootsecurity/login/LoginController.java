package com.franktran.springbootsecurity.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/show-login")
    public String showLogin(@ModelAttribute("account") Account account) {
        return "login";
    }
}
