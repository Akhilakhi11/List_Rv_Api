package com.example.list_rv_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tvname)
    TextView tvname;
    @BindView(R.id.rvlist)
    RecyclerView rvlist;
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
                        public void OnItemClick(DataItem name) {

                            Intent intent = new Intent(MainActivity.this,Activity2.class);
                            intent.putExtra("imgnet",name.getAvatar());
                            intent.putExtra("fnamenet",name.getFirstName());
                            intent.putExtra("lnamenet",name.getLastName());
                            intent.putExtra("emailnet",name.getEmail());
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

}