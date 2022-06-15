package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommentflowchartui.DTO.Friend;
import com.example.recommentflowchartui.DTO.Interest;
import com.example.recommentflowchartui.DTO.Post;
import com.example.recommentflowchartui.DTO.Stringring;
import com.example.recommentflowchartui.DTO.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendPage extends AppCompatActivity {
    private Button friendlist;
    private Button breakfriend;
    private ArrayList<CategoryData> arrayList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView friendprofile;
    private TextView Nickname;
    private String friendNick;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_friend_page);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        Nickname=(TextView) findViewById(R.id.nickname);

        String userId =  getIntent().getSerializableExtra("userId").toString();
        String friendId = getIntent().getSerializableExtra("friendId").toString();
        friendNick = getIntent().getSerializableExtra("friendNick").toString();
        User friend;

        initView(friendId);
        setRecyclerView(friendId);
        setOnclick();
    }

    private void initView(String friendId){
        friendlist=(Button) findViewById(R.id.seefriend);
        breakfriend=(Button) findViewById(R.id.breakfriend);
        friendprofile=(TextView)findViewById(R.id.friendprofile);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView4);
        Nickname.setText(friendNick);


    }

    private void setOnclick(){
        breakfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //나의 계정 친구데이터베이스에서 친구 삭제
                deleteFriend();
            }
        });
        friendlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FriendPage.this,Friendlist.class);
                startActivity(intent);
            }
        });
        friendprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FriendPage.this,SeeProfile.class);
                startActivity(intent);
            }
        });
    }

    private void setRecyclerView(String friendId){
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList=new ArrayList<>();

        Stringring userid=new Stringring(friendId);
        Call<List<Interest>> call2 = RetrofitClient.getInstance().getMyApi().getInterestByUserId(userid);
        call2.enqueue(new Callback<List<Interest>>() {
            @Override
            public void onResponse(Call<List<Interest>> call2, Response<List<Interest>> response) {

                List<Interest> interestList = response.body();


                for(int i=0; i<interestList.size();i++){
                    CategoryData categoryData=new CategoryData(R.drawable.star,interestList.get(i).getSingleInterest(), friendId);
                    arrayList.add(categoryData);
                }

                categoryAdapter=new CategoryAdapter(arrayList);
                recyclerView.setAdapter(categoryAdapter);

            }
            @Override
            public void onFailure(Call<List<Interest>> call2, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
            }
        });




    }

    private void deleteFriend(){
        String userId = (String) getIntent().getSerializableExtra("userId");
        String friendId = (String) getIntent().getSerializableExtra("friendId");

        Call<Friend> call = RetrofitClient.getInstance().getMyApi().deleteOneFriend(userId, friendId);

        call.enqueue(new Callback<Friend>() {
            @Override
            public void onResponse(Call<Friend> call, Response<Friend> response) {
//                Intent intent=new Intent(Loginpage.this,Mainpage.class);
//                intent.putExtra("userId", userId);
//                startActivity(intent);
                finish();
                Log.i("fuck", "friend delete succeed");
            }

            @Override
            public void onFailure(Call<Friend> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured in deleting friend", Toast.LENGTH_LONG).show();
            }
        });
    }
}