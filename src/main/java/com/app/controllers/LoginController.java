package com.app.controllers;

import com.app.model.Login;
import com.app.services.LangService;
import com.app.services.LoginService;
import com.app.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private LangService langService;

    @Autowired
    CurrentUser currentUser;

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        if (currentUser.getId() != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        if (currentUser.getId() != null) {
            model.addAttribute("id", currentUser.getId());
            return "login_response";
        }
        model.addAttribute("loginObject", new Login());
        return "login";
    }

    @GetMapping("/translations")
    public String getTranslationsPage(Model model) {
        if (currentUser.getLangId() == null) {
            currentUser.setLangId(1);
        }
        model.addAttribute("lang", langService.getTranslations(currentUser.getLangId(), "homePage"));
        return "translations";
    }

    @GetMapping("/setLang/{langId}")
    @ResponseBody
    public void setLang(@PathVariable(value = "langId") int langId) {
        currentUser.setLangId(langId);
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute Login loginObject, Model model) {
        Integer userId = loginService.getUserId(loginObject);
        if (userId != null) {
            currentUser.setId(userId);
            currentUser.setName(loginObject.getUsername());
            model.addAttribute("id", userId);
            return "login_response";
        }
        return "login";
    }
}
