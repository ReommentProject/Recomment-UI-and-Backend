package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendPage extends AppCompatActivity {
    private Button friendlist;
    private Button breakfriend;
    private ArrayList<CategoryData> arrayList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView friendprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_friend_page);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        friendlist=(Button) findViewById(R.id.seefriend);
        breakfriend=(Button) findViewById(R.id.breakfriend);
        friendprofile=(TextView)findViewById(R.id.friendprofile);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView4);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList=new ArrayList<>();
        categoryAdapter=new CategoryAdapter(arrayList);
        recyclerView.setAdapter(categoryAdapter);
        for(int i=0 ; i<10 ; i++)
        {
            CategoryData categoryData=new CategoryData(R.drawable.star,"카테고리");
            arrayList.add(categoryData);
        }
        breakfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //나의 계정 친구데이터베이스에서 친구 삭제
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
}