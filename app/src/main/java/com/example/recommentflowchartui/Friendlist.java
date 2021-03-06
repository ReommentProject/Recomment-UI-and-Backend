package com.example.recommentflowchartui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.recommentflowchartui.DTO.Friend;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Friendlist extends AppCompatActivity {

    private ArrayList<friendData> arrayList;
    private FriendAdapter friendAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_friendlist);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerView = (RecyclerView) findViewById(R.id.friendlist);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayList.clear();
        getFriends(arrayList);
    }

    private void getFriends(List<friendData> array) {

        String userId = (String) getIntent().getSerializableExtra("userId");

        Log.d("fuckuserId", userId);
//        Intent intent=new Intent(Signup_page2.this,Signup_finish.class);
//        intent.putExtra("createdUser", cUser);
//        startActivity(intent);

        Call<List<Friend>> call = RetrofitClient.getInstance().getMyApi().getFriends();

        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {

                List<Friend> friendsList = response.body();

                for (int i = 0; i < friendsList.size(); i++) {
                    if (friendsList.get(i).getUser_Id().equals(userId)) {
                        String myFriend = friendsList.get(i).getFriend_Id();
                        friendData friend1 = new friendData(R.mipmap.ic_launcher, "" + myFriend, "cbnu friend", userId);
                        array.add(friend1);
                    }
                }
                friendAdapter = new FriendAdapter(arrayList);
                recyclerView.setAdapter(friendAdapter);
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
            }
        });
    }
}

