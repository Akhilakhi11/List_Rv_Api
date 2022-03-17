package com.example.list_rv_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.edpswd)
    EditText edpswd;
    @BindView(R.id.eduser)
    EditText eduser;
    @BindView(R.id.btnreg)
    Button btnreg;
    SharedPreferences preferences ;


//    private static final String KEYMAIL = "mail";
//    private static final String KEYPSWD = "pswd";
    private static final String KEYTOKEN = "tkn";
    //private static final String MODE_PRIVATE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        ButterKnife.bind(this);

        preferences = getSharedPreferences("SHAREDMAIL", MODE_PRIVATE);
        String tkn = preferences.getString(KEYTOKEN, null);
        if (tkn != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
            @OnClick(R.id.btnreg)
            public void onCreateBtnPressed() {
                LoginRequest login = new LoginRequest();
                login.setEmail(eduser.getText().toString());
                login.setPassword(edpswd.getText().toString());

                Interface_Api methods = RetrofitClient.getRetrofitInstance().create(Interface_Api.class);
                Call<LoginResponse> call = methods.postUser(login);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) ;
                        Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(KEYTOKEN,response.body().getToken());
                        editor.apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
