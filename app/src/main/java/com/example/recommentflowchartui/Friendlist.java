package com.example.recommentflowchartui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Friendlist extends AppCompatActivity {

    private List<friendData> arrayList;
    private FriendAdapter friendAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerView = (RecyclerView) findViewById(R.id.friendlist);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        getFriends(arrayList);

//        for (int i = 0; i < 50; i++) {
//            friendData friendData = new friendData(R.mipmap.ic_launcher, "친구 닉네임", "관심있는 카테고리");
//            arrayList.add(friendData);
//        }
    }


    private void getFriends(List<friendData> array) {

        Call<List<Friend>> call = RetrofitClient.getInstance().getMyApi().getFriends();

        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {

                List<Friend> friendsList = response.body();
                Log.i("fuck", "this fucks!"+array.size());

                for(int i=0;i<friendsList.size();i++){
                    friendsList.get(i).getUser_Id();
                    String myFriend = friendsList.get(i).getFriend_Id();
                    friendData friend1 = new friendData(R.mipmap.ic_launcher, ""+myFriend, "fuck!");
                    array.add(friend1);
                }

                friendAdapter = new FriendAdapter(arrayList);
                recyclerView.setAdapter(friendAdapter);

                Log.i("fuck", ""+array.size());
//                Log.i("fuck", ""+array.get(1).getContent()+array.get(54).getContent());


//                HelperAdapter helperAdapter = new HelperAdapter(getContext(), postList);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                recyclerView.setLayoutManager(linearLayoutManager);
//                recyclerView.setAdapter(helperAdapter);
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
            }

        });

    }
}

