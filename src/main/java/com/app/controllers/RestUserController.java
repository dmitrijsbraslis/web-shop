package com.app.controllers;

import com.app.model.Users;
import com.app.services.UserService;
import com.app.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestUserController {

    @Autowired
    UserService userService;

    @Autowired
    CurrentUser currentUser;

    @CrossOrigin
    @GetMapping("/getUsers")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @GetMapping("/getUsername")
    public int getUsername() {
        return currentUser.getId();
    }
}
