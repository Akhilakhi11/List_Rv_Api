package com.example.list_rv_api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_Api {

    @GET("users")
    Call<ProductListresponse>getAllData();
}
