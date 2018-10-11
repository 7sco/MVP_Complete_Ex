package com.example.franciscoandrade.loginapp;

import com.example.franciscoandrade.loginapp.login.LoginActivityMVP;
import com.example.franciscoandrade.loginapp.login.LoginActivityPresenter;
import com.example.franciscoandrade.loginapp.login.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

        user= new User("Manolo", "Escobar");
        User user1= new User("Antonio", "Banderas");

        //when someone calls to method getUser
        //then return user ("Antonio", "Banderas")
        //when(mockedModel.getUser()).thenReturn(user1);

        //when mockedview.getfirstName
        //then return antonio
        //when(mockedView.getFirstName()).thenReturn("Manolo");

        //when mockedview.getlastName
        //then return banderas
        //when(mockedView.getLastName()).thenReturn("Escobar");

        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }

    @Test
    public void noExistInteractionWithView(){
        presenter.getCurrentUser();
        //runs if get user was not run, if it was it will fail
        /**
         * Verify there is zero interactions with the view
         */
        //verifyZeroInteractions(mockedView);
        //OR
        //verify(mockedView, never()).showUserNotAvailable();
        //comment show toast because it still interacts with view

        /**
         * Verify that mockedView calls exactly 1 time to the method showUserNotAvailable()
         * Should work when we dont have a user created
         */
        verify(mockedView, times(1)).showUserNotAvailable();
    }

    /**
     * Test that loads User from repo when user there is a user loged in
     */
    @Test
    public void loadUserFromTheRepoWhenValidUserIsPresent(){
        User user= new User("Manolo", "Escobar");
        when(mockedModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();
        //Test interaction with data model
        verify(mockedModel, times(1)).getUser();
        //Test interaction with the View
        verify(mockedView, times(1)).setFirstName("Manolo");
        verify(mockedView, times(1)).setLastName("Escobar");
        verify(mockedView, never()).showUserNotAvailable();

        /**
         * 1. When model gets asked for the user return user created Manolo Escobar
         * 2. run  method gerUser from presenter
         * 3. Verify that getUser() is called only once
         * 4. Verify that interacts with the View sending  Manolo as firstName
         * 5. Verify that interacts with the View sending  Escobar as lastName
         * 6. Verify that it does not call showUserNotAvailable() never
         */
    }

    /**
     * Test that verifies that error message is shown when there is no user
     */
    @Test
    public void showErrorMessageWhenUserIsNull(){
        when(mockedModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();
        verify(mockedView, times(1)).showUserNotAvailable();
        verify(mockedView, never()).setFirstName("Manolo");
        verify(mockedView, never()).setLastName("Escobar");

        /**
         * 1. When model gets asked for the user and returns null
         * 2. run  method gerUser from presenter
         * 3. Verify it calls once showUserNotAvailable()
         * 4. Verify that it does not call setFirstName()
         * 5. Verify that it does not call setLastName()
         */
    }


    /**
     * Test if FirstName or LastName fields are empty and call showInputError() method in the view
     */
    @Test
    public void createErrorMessageIfAnyFieldIsEmpty(){
        //First test with empty field for FirstName
        when(mockedView.getFirstName()).thenReturn("");
        presenter.loginButtonClicked();
        verify(mockedView, times(1)).getFirstName();
        verify(mockedView, never()).getLastName();
        verify(mockedView, times(1)).showInputError();

        //Second test with empty field for LastName and filled FirstName
        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("");
        presenter.loginButtonClicked();
        //Test accumulates 1 for this test and one for the previous in the same method, method didnt finished yet
        verify(mockedView, times(2)).getFirstName();
        verify(mockedView, times(1)).getLastName();//FIrst time it gets called because last time it didt pass boolean value
        verify(mockedView, times(2)).showInputError();//Method is called twice as explained before because it accumulate
    }

    /**
     *
     */
    @Test
    public void saveValidUser(){
       when(mockedView.getFirstName()).thenReturn("Francisco");
       when(mockedView.getLastName()).thenReturn("Andrade");
       presenter.loginButtonClicked();
       //Calls have to be double one in the if and other when u create user
       verify(mockedView, times(2)).getFirstName();
       verify(mockedView, times(2)).getLastName();

       //Look that model persist in the repository
       verify(mockedModel, times(1)).createUser("Francisco", "Andrade");
       verify(mockedView, times(1)).showUserSaved();
       verify(mockedView, never()).showInputError();
    }
}
