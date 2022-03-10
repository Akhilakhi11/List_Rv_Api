package com.example.list_rv_api;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity4 extends AppCompatActivity {
    @BindView(R.id.nametv)
    TextView nametv;
    @BindView(R.id.jobtv)
    TextView jobtv;
    @BindView(R.id.nameed)
    EditText nameed;
    @BindView(R.id.jobed)
    EditText jobed;
    @BindView(R.id.login)
    Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        ButterKnife.bind(this);


    }
   @OnClick(R.id.login)
    public void onCreateBtnPressed(){
        UserRequest user = new UserRequest();
        user.setName(nameed.getText().toString());
        user.setJob(jobed.getText().toString());

       Interface_Api methods = RetrofitClient.getRetrofitInstance().create(Interface_Api.class);
       Call<UserResponse> call = methods.postUser(user);
       call.enqueue(new Callback<UserResponse>() {
           @Override
           public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
               if (response.isSuccessful()){
                   Toast.makeText(Activity4.this,response.body().getId(),Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<UserResponse> call, Throwable t) {
               Toast.makeText(Activity4.this, t.getMessage(), Toast.LENGTH_SHORT).show();


           }
       });
   }
}