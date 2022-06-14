package com.example.recommentflowchartui;

import com.example.recommentflowchartui.DTO.Friend;
import com.example.recommentflowchartui.DTO.Intt;
import com.example.recommentflowchartui.DTO.Post;
import com.example.recommentflowchartui.DTO.User;

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
//    String BASE_URL = "http://118.46.38.174:8000/";

    String BASE_URL = "http:/172.30.3.186:9001/";

    @GET("user")
    Call<List<User>> getUsers();

    @GET("user/{id}")
    Call<User> getOneUser(@Path("id") String userId);

    @POST("user")
    Call<User> createUser(@Body User user);

    @GET("post")
    Call<List<Post>> getPosts();

    @POST("post/upLike")
    Call<Post> upLike(@Body Intt id);

    @POST("post/downLike")
    Call<Post> downLike(@Body Intt id);

    @GET("friend")
    Call<List<Friend>> getFriends();


}
