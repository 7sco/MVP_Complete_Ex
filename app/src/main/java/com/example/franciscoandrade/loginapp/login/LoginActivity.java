package com.example.franciscoandrade.loginapp.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.franciscoandrade.loginapp.R;
import com.example.franciscoandrade.loginapp.root.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.lastNameET)
    EditText lastNameET;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((App) getApplicationContext()).getComponent().inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        presenter.loginButtonClicked();
    }

    @Override
    public String getFirstName() {
        return nameET.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastNameET.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Error user not available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Error Name or Last Name cant be empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setFirstName(String firstName) {
        nameET.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        lastNameET.setText(lastName);
    }
}
