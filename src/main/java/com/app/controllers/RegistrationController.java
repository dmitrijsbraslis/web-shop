package com.app.controllers;

import com.app.model.Registration;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("registration", new Registration());
        return "registration";
    }

    @PostMapping("/registration")
    public String submitRegistrationForm(@ModelAttribute Registration myFirstBestRegistrationEver, Model model) {
        model.addAttribute("regData", myFirstBestRegistrationEver);
        userService.storeUser(myFirstBestRegistrationEver);
        return "success";
    }
}
