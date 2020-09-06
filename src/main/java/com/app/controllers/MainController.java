package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/hello")
    public String getHelloPage(Model model) {
        model.addAttribute("name", "Java");
        return "hello";
    }
}
