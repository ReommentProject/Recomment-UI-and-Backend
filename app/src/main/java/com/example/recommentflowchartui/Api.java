package com.example.recommentflowchartui;

import com.example.recommentflowchartui.DTO.Friend;
import com.example.recommentflowchartui.DTO.Intt;
import com.example.recommentflowchartui.DTO.Post;
import com.example.recommentflowchartui.DTO.Stringring;
import com.example.recommentflowchartui.DTO.User;
import com.example.recommentflowchartui.DTO.Interest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {


    // 노바 동방 전용
    String BASE_URL = "http://113.198.137.183:7999/";
    // 애니웨얼
  //  String BASE_URL = "http://118.46.38.174:8000/";

//    String BASE_URL = "http:/172.30.3.186:9001/";

    // user
    // 모든 유저 가져오기
    @GET("user")
    Call<List<User>> getUsers();

    // 아이디로 유저 가져오기
    @GET("user/{id}")
    Call<User> getOneUser(@Path("id") String userId);

    // 유저 생성
    @POST("user")
    Call<User> createUser(@Body User user);

    // post
    // 모든 포스트 가져오기
    @GET("post")
    Call<List<Post>> getPosts();

    // 좋아요 1 올리기
    @POST("post/upLike")
    Call<Post> upLike(@Body Intt id);

    // 좋아요 1 떨구기
    @POST("post/downLike")
    Call<Post> downLike(@Body Intt id);

    // friend
    // 모든 친구 가져오기
    @GET("friend")
    Call<List<Friend>> getFriends();

    // interest
    // 유저 아이디로 관심분야 가져오기
    @POST("interests/fuck")
    Call<List<Interest>> getInterestByUserId(@Body Stringring user_id);

    // 관심분야 추가하기
    @POST("interests")
    Call<Interest> createInterest(@Body Interest single);

}
