package com.example.franciscoandrade.loginapp.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model){
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model providesLoginActivityModel(LoginRepository repository){
        return new LoginActivityModel(repository);
    }

    @Provides
    public LoginRepository providesLoginRepository(){
        //Change here if we want to replace the repo in memory with a DB, cloud storage ...
        return new MemoryRepository();
    }

}
