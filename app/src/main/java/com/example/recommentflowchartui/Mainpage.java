package com.example.recommentflowchartui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.recommentflowchartui.DTO.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Mainpage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mainpage);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        String userId;
        if(getIntent().getSerializableExtra("createdUser")!=null){

            User createdUser = (User) getIntent().getSerializableExtra("createdUser");
            userId = createdUser.getUser_Id();
        }
        else {
            userId = (String) getIntent().getSerializableExtra("userId");
        }
        Bundle bundle = new Bundle();
        bundle.putString("userId",userId);

        if(getIntent().getSerializableExtra("userId")!=null){
            bundle.putString("userId",getIntent().getSerializableExtra("userId").toString());
        }
        Fragment fragment = new Frag1();
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();

        bottomNavigationView=findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.action_friends:
                        //fragment= new Fragment1();
                        fragment = new Frag1();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.main_frame, fragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.action_usergroup:
                        fragment = new Frag2();
                        fragment.setArguments(bundle);
                        break;
                    case R.id.action_Mypage:
                        fragment = new Frag3();
                        fragment.setArguments(bundle);
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();

//                String userId = (String) getIntent().getSerializableExtra("userId");
//                Bundle bundle = new Bundle();
//                bundle.putString("userId",userId);
//                fragment.setArguments(bundle);
                Log.d("fuckkkk", userId);

                return true;
            }
        });
        /*frag1=new Frag1();
        frag2=new Frag2();
        frag3=new Frag3();
        setFrag(0);//첫 메인페이지 화면*/
    }

    //프래그먼트 교체가 일어나는 실행문
   /* private void setFrag(int n){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.main_frame,frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,frag3);
                ft.commit();
                break;
        }
    }*/
}