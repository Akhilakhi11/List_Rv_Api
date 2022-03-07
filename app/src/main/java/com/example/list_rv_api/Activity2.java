package com.example.list_rv_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity2 extends AppCompatActivity {
    @BindView(R.id.img_details)
    ImageView img_details;
    @BindView(R.id.fname_details)
    TextView fname_details;
    @BindView(R.id.lname_details)
    TextView lname_details;
    @BindView(R.id.email_details)
    TextView email_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("imgnet")) {
            String imageUrl = getIntent().getStringExtra("imgnet");
            Glide.with(this).asBitmap().load(imageUrl).into(img_details);
        }

        fname_details.setText(getIntent().getStringExtra("fnamenet"));
        lname_details.setText(getIntent().getStringExtra("lnamenet"));
        email_details.setText(getIntent().getStringExtra("emailnet"));

    }
}