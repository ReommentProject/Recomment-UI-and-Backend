package com.example.recommentflowchartui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {


    // 노바 동방 전용
//    String BASE_URL = "http://113.198.137.183:7999/";
    // 애니웨얼
    String BASE_URL = "http://118.46.38.174:8000/";

    @GET("user")
    Call<List<User>> getUsers();

    @GET("user/{id}")
    Call<User> getOneUser(@Path("id") String userId);

    @POST("user")
    Call<User> createUser(@Body User user);

    @GET("post")
    Call<List<Content>> getPosts();

    @GET("friend")
    Call<List<Friend>> getFriends();


}
