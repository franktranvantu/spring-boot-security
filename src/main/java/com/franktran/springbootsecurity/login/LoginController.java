package com.franktran.springbootsecurity.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/show-login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/process-login")
    @ResponseBody
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        return String.format("Username: %s, Password: %s", username, password);
    }
}
