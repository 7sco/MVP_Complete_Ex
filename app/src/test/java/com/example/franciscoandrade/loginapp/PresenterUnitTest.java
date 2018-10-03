package com.example.franciscoandrade.loginapp;

import com.example.franciscoandrade.loginapp.login.LoginActivityMVP;
import com.example.franciscoandrade.loginapp.login.LoginActivityPresenter;
import com.example.franciscoandrade.loginapp.login.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;
    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    @Before
    public void initialization(){

        mockedModel= mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);

        User user= new User("Manolo", "Escobar");

        //when someone calls to method getUser
        //then return user ("Antonio", "Banderas")
        //when(mockedModel.getUser()).thenReturn(user);

        //when mockedview.getfirstName
        //then return antonio
        when(mockedView.getFirstName()).thenReturn("Manolo");

        //when mockedview.getlastName
        //then return banderas
        when(mockedView.getLastName()).thenReturn("Escobar");

        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }

    @Test
    public void noExistInteractionWithView(){
        presenter.getCurrentUser();
        verifyZeroInteractions(mockedView);
        //comment show toast because it still interacts with view
    }


}
