package com.example.franciscoandrade.loginapp.root;

import com.example.franciscoandrade.loginapp.login.LoginActivity;
import com.example.franciscoandrade.loginapp.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);
}
