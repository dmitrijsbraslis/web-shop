package com.app.services;

import com.app.dao.UserDao;
import com.app.model.Registration;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        List<User> users = userDao.getUsers();

        users.stream()
                .filter(user -> user.getUsername().equals("Klimov1970"))
                .limit(3)

                .forEach(System.out::println);

        return userDao.getUsers();
    }

    public String getFirstUserUsername() {
        return userDao.getUsers().get(0).getUsername();
    }

    public void storeUser(Registration reg) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        reg.setPassword(encoder.encode(reg.getPassword()));

        userDao.storeUser(reg);
    }
}
