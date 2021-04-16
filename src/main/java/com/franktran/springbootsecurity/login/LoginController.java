package com.franktran.springbootsecurity.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/show-login")
    public String showLogin(@ModelAttribute("account") Account account) {
        return "login";
    }

    @PostMapping("/process-login")
    @ResponseBody
    public Account processLogin(Account account) {
        return account;
    }
}
