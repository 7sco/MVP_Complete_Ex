package com.example.franciscoandrade.loginapp.login;

import com.example.franciscoandrade.loginapp.login.model.User;

public interface LoginRepository {

    void saveUser(User user);
    User getUser();
}
