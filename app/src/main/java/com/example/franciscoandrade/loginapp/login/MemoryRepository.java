package com.example.franciscoandrade.loginapp.login;

import com.example.franciscoandrade.loginapp.login.model.User;

public class MemoryRepository implements LoginRepository {

    //This user can be replaced with DB , Firebase,  etc
    private User user;

    @Override
    public void saveUser(User user) {
        if (user==null){
            user= getUser();
        }
        this.user= user;
    }

    @Override
    public User getUser() {

        if (user==null){
            user = new User("Jhon", "Doe");
            user.setId(0);
            return user;
        }else {
            return user;
        }
    }
}
