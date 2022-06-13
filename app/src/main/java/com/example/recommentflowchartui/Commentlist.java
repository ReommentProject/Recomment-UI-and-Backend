package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Commentlist extends AppCompatActivity {

    private ArrayList<CommentData> arrayList;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Button makecomment;
    private ImageView commentprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_commentlist);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        makecomment=(Button)findViewById(R.id.makecomment);
        commentprofile=(ImageView)findViewById(R.id.commentprofile);

        recyclerView=(RecyclerView) findViewById(R.id.commentlist);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList=new ArrayList<>();
        commentAdapter=new CommentAdapter(arrayList);
        recyclerView.setAdapter(commentAdapter);
        for(int i=0 ; i<50 ; i++)
        {
            CommentData commentData=new CommentData(R.drawable.mypage,"Nickname","너무 좋은 영상을 추천해줘서 감사합니다!!");
            arrayList.add(commentData);
        }
        makecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Commentlist.this,MakeComments.class);
                startActivity(intent);
            }
        });


    }
}