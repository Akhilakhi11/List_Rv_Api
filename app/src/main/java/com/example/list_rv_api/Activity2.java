package com.example.list_rv_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
    @BindView(R.id.email_details)
    TextView email_details;
    ProductListresponse.DataItem dataItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ButterKnife.bind(this);

        dataItem = getIntent().getParcelableExtra("dataitem");
//        if (getIntent().hasExtra("imgnet")) {
//            String imageUrl = getIntent().getStringExtra("imgnet");
//            Glide.with(this).asBitmap().load(imageUrl).into(img_details);
//        }

        fname_details.setText(dataItem.getFirstName()+" "+dataItem.getLastName());
        email_details.setText(dataItem.getEmail());
        Glide.with(this).asBitmap().load(dataItem.getAvatar()).into(img_details);

    }
}