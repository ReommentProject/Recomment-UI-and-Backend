package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Frag3 extends Fragment {

    private View view;
    private Button friendlist;
    private Button recomment;
    private ArrayList<CategoryData> arrayList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView profile;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag3,container,false);
        friendlist=view.findViewById(R.id.seefriend);
        recomment=view.findViewById(R.id.goupload);
        profile=view.findViewById(R.id.myprofile);
        recyclerView=view.findViewById(R.id.recyclerView3);
        linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList=new ArrayList<>();
        categoryAdapter=new CategoryAdapter(arrayList);
        recyclerView.setAdapter(categoryAdapter);

        //RequestActivity에서 전달한 번들 저장
        Bundle bundle = getArguments();
        //번들 안의 텍스트 불러오기
        String userId = bundle.getString("userId");


        for(int i=0 ; i<10 ; i++)
        {
            CategoryData categoryData=new CategoryData(R.drawable.star,"카테고리");
            arrayList.add(categoryData);
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),SeeProfile.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        recomment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),upload_page.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        friendlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Friendlist.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        return view;
    }
}
