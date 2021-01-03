package com.app.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Registration {
    private String username;
    private String password;
    private LocalDate birthDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.birthDate = LocalDate.parse(birthDate, formatter);
    }
}
