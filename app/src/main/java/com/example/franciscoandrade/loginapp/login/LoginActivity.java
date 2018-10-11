package com.example.franciscoandrade.loginapp.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.franciscoandrade.loginapp.R;
import com.example.franciscoandrade.loginapp.http.TwitchApi;
import com.example.franciscoandrade.loginapp.http.twitch.Data;
import com.example.franciscoandrade.loginapp.http.twitch.Twitch;
import com.example.franciscoandrade.loginapp.root.App;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.lastNameET)
    EditText lastNameET;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Inject
    TwitchApi twitchApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((App) getApplicationContext()).getComponent().inject(this);

        //Use of Twitch Api with retrofit
        Call<Twitch> call = twitchApi.getTopGames("YOUR ID HERE");
        call.enqueue(new Callback<Twitch>() {
            @Override
            public void onResponse(Call<Twitch> call, Response<Twitch> response) {
                List<Data> topGames =response.body().getData();
                for (Data game:topGames){
                    Log.d("Games=", "onResponse: "+ game);
                }
            }

            @Override
            public void onFailure(Call<Twitch> call, Throwable t) {
                t.printStackTrace();
            }
        });
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
