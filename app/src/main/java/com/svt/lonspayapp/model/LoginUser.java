package com.svt.lonspayapp.model;

import android.util.Patterns;

public class LoginUser {
    private String emailId;
    private String password;

    public LoginUser(String uName, String pass) {
        this.emailId = uName;
        this.password = pass;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmailId()).matches();
    }

    public boolean isPasswordGreaterthan8() {
        return getPassword().length() >= 8;
    }
}
