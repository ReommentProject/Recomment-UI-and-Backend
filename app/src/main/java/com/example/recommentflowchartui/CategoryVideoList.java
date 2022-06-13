package com.example.recommentflowchartui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        arrayList=new ArrayList<>();
        categoryVideoAdapter=new CategoryVideoAdapter(arrayList);
        recyclerView.setAdapter(categoryVideoAdapter);
        for(int i=0 ; i<50 ; i++)
        {
            CategoryVideoData categoryVideoData=new CategoryVideoData(R.drawable.friends,"Title","Review");
            arrayList.add(categoryVideoData);
        }


    }
}