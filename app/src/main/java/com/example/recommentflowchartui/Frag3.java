package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommentflowchartui.DTO.Friend;
import com.example.recommentflowchartui.DTO.Interest;
import com.example.recommentflowchartui.DTO.Post;
import com.example.recommentflowchartui.DTO.Stringring;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        //RequestActivity에서 전달한 번들 저장
        Bundle bundle = getArguments();
        //번들 안의 텍스트 불러오기
        String userId = bundle.getString("userId");


        Stringring userid=new Stringring(userId);
        Call<List<Interest>> call = RetrofitClient.getInstance().getMyApi().getInterestByUserId(userid);
        call.enqueue(new Callback<List<Interest>>() {
            @Override
            public void onResponse(Call<List<Interest>> call, Response<List<Interest>> response) {

                List<Interest> interestList = response.body();


                for(int i=0; i<interestList.size();i++){
                    CategoryData categoryData=new CategoryData(R.drawable.star,interestList.get(i).getSingleInterest());
                    arrayList.add(categoryData);
                }

                categoryAdapter=new CategoryAdapter(arrayList);
                recyclerView.setAdapter(categoryAdapter);

            }
            @Override
            public void onFailure(Call<List<Interest>> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
            }
        });


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
