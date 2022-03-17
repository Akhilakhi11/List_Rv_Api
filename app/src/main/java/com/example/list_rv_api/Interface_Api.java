package com.example.list_rv_api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Interface_Api {


    @GET("users")
    Call<ProductListresponse> getAllData();

    @POST("users")
    Call<UserResponse> postUser(@Body  UserRequest userRequest);

    @POST("login")
    Call<LoginResponse> postUser(@Body LoginRequest userResponse);

}