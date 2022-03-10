package com.example.list_rv_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity3 extends AppCompatActivity {
    @BindView(R.id.edpswd)
    EditText edpswd;
    @BindView(R.id.eduser)
    EditText eduser;
    @BindView(R.id.btnreg)
    Button btnreg;


    private static final String KEYMAIL = "email";
    private static final String KEYPSWD = "pswd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences("SHAREDNAME", MODE_PRIVATE);
        String email = preferences.getString(KEYMAIL, null);
        if (email != null) {
            Intent intent = new Intent(Activity3.this, MainActivity.class);
            startActivity(intent);
        }


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity3.this, MainActivity.class);
                startActivity(intent);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(KEYMAIL, eduser.getText().toString());
                editor.putString(KEYPSWD, edpswd.getText().toString());
                editor.apply();

            }
        });
    }
}