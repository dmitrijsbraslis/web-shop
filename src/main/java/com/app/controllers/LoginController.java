package com.app.controllers;

import com.app.model.Login;
import com.app.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("loginObject", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute Login loginObject, Model model) {
        Integer userId = loginService.getUserId(loginObject);
        if (userId != null) {
            model.addAttribute("id", userId);
            return "login_response";
        }
        return "login";
    }
}
