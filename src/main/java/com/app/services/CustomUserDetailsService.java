package com.app.services;

import com.app.dao.UserDao;
import com.app.model.CustomUserDetails;
import com.app.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users users = userDao.getUsersByUsername(username).get(0);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(users);
    }
}
