package com.example.recommentflowchartui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommentflowchartui.DTO.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryVideoList extends AppCompatActivity {

    private ArrayList<CategoryVideoData> arrayList;
    private CategoryVideoAdapter categoryVideoAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category_video_list);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        recyclerView=(RecyclerView) findViewById(R.id.categoryvideo);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        Call<List<Post>> call = RetrofitClient.getInstance().getMyApi().getPosts();

        Context temp = this;

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                List<Post> postList = response.body();
                HelperAdapter helperAdapter = new HelperAdapter(temp, postList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(temp);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(helperAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(temp, "An error has occured in get", Toast.LENGTH_LONG).show(); }

        });


    }
}