package com.franktran.springbootsecurity.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/show-login")
    public String showLogin(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping("/process-login")
    public void processLogin(User user) {
        System.out.println(user);
    }
}
