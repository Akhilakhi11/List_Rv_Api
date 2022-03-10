package com.example.list_rv_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.BreakIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tvname)
    TextView tvname;
    @BindView(R.id.rvlist)
    RecyclerView rvlist;
    @BindView(R.id.fabbtn)
    FloatingActionButton fabbtn;
    private Object thumbnail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Interface_Api methods = RetrofitClient.getRetrofitInstance().create(Interface_Api.class);
        Call<ProductListresponse> call = methods.getAllData();
        call.enqueue(new Callback<ProductListresponse>() {

            @Override
            public void onResponse(Call<ProductListresponse> call, Response<ProductListresponse> response) {
                if (response.isSuccessful()) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    rvlist.setLayoutManager( linearLayoutManager);
                    MyAdapter myAdapter = new MyAdapter(MainActivity.this, response.body().getData());
                    rvlist.setAdapter(myAdapter);
                    Log.i("response", String.valueOf(response.body().getData()));

                    myAdapter.OnRecyclerViewClickListener(new MyAdapter.OnRecyclerViewClickListener() {

                        @Override
                        public void OnItemClick(ProductListresponse.DataItem name) {

                            Intent intent = new Intent(MainActivity.this, Activity2.class);
                            intent.putExtra("dataitem", name);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ProductListresponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @OnClick(R.id.fabbtn)
    public void onAddClick (View v){
        Intent intent = new Intent(MainActivity.this, Activity4.class);
        startActivity(intent);
    }

}