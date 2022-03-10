package com.example.list_rv_api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://reqres.in/api/";

    public static Retrofit getRetrofitInstance() {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }


    }


