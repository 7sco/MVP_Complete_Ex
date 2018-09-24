package com.example.franciscoandrade.loginapp.login;

import com.example.franciscoandrade.loginapp.login.model.User;

public interface LoginActivityMVP {

    interface View {
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();

        void setFirstName(String firstName);
        void setLastName(String lastName);
    }

    interface Presenter {
        void setView(LoginActivityMVP.View view);

        void loginButtonClicked();

        void getCurrentUser();

    }

    interface Model {
        void createUser(String firstName, String lastName);

        User getUser();
    }
}