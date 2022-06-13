package com.example.recommentflowchartui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag1 extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag1, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);



        Bundle bundle = getArguments();
        String userId = bundle.getString("userId");


        return getFriendsPosts(view, userId);

//        Log.i("fuck", "image size : "+images.size());
//        Log.i("fuck", "name size : "+name.size());


    }

    private View getFriendsPosts(View view, String userId) {

        Call<List<Content>> call = RetrofitClient.getInstance().getMyApi().getPosts();
//        Call<List<Friend>> call2 = RetrofitClient.getInstance().getMyApi().getFriends();

        call.enqueue(new Callback<List<Content>>() {
            @Override
            public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {

                List<Content> postList = response.body();

                Call<List<Friend>> call2 = RetrofitClient.getInstance().getMyApi().getFriends();
                call2.enqueue(new Callback<List<Friend>>() {
                    @Override
                    public void onResponse(Call<List<Friend>> call2, Response<List<Friend>> response2) {
                        List<Friend> friendsList = response2.body();

                        List<Content> friendsPostList = new ArrayList<>();
                        List<Friend> myFriendList = new ArrayList<>();
//
                        Log.d("fuckIDID",userId);

                        for(int i=0;i<friendsList.size();i++){
                            if(friendsList.get(i).getUser_Id().equals(userId)) {
                                myFriendList.add(friendsList.get(i));
                            }
                        }

                        Log.d("fucksize",""+postList.size());
                        Log.d("fucksize",""+myFriendList.size());

                        for(int j=0;j<postList.size();j++) {
                            for (int i = 0; i < myFriendList.size(); i++) {
                                if (myFriendList.get(i).getFriend_Id().equals(postList.get(j).getUser_Id())) {
                                    friendsPostList.add(postList.get(j));
                                }
                            }
                        }



                        HelperAdapter helperAdapter = new HelperAdapter(getContext(), friendsPostList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(helperAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<Friend>> call2, Throwable t2) {
                        Toast.makeText(getContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
                    }

                });

            }

            @Override
            public void onFailure(Call<List<Content>> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
            }

        });

        return view;
    }
}