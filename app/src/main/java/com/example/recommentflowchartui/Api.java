package com.example.recommentflowchartui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "http://118.46.38.174:8000/";

    @GET("user")
    Call<List<User>> getUsers();

    @POST("user")
    Call<User> createUser(@Body User user);


    @GET("post")
    Call<List<Content>> getPosts();


}
