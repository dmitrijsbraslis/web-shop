package com.app.controllers;

import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String getHelloPage(Model model) {
        model.addAttribute("name", userService.getFirstUserUsername());
        return "hello";
    }
}
