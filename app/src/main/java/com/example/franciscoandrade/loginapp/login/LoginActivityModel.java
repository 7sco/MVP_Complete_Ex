package com.example.franciscoandrade.loginapp.login;

import com.example.franciscoandrade.loginapp.login.model.User;

public class LoginActivityModel implements LoginActivityMVP.Model {

    //Injected by dagger
    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        //Business logic:
        // check user exist/ doesnt exist
        //User correct
        //email is correct validation

        //then save user:
        repository.saveUser(new User(firstName, lastName));
    }

    @Override
    public User getUser() {
        //logic business
        return repository.getUser();
    }
}
