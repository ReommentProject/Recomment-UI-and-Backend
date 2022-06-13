package com.example.recommentflowchartui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class uploadcategory extends AppCompatActivity {
    private Button finishupload;
    ListView listViewData;
    ArrayAdapter<String>adapter;
    String[]arrayPeliculas={"Science" , "History" ,"Sports" ,"Music" ,"Entertainment" ,"Game" , "Animal" ,"Cooking" ,"Movie" ,"Drama"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_uploadcategory);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        finishupload=findViewById(R.id.finishupload);
        listViewData=findViewById(R.id.listView_data);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,arrayPeliculas);
        listViewData.setAdapter(adapter);
        String userId = (String) getIntent().getSerializableExtra("userId");
        Log.d("fuckgetid",userId);
        finishupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getIntent().getSerializableExtra("createdUser") != null) {
                    User cUser = (User) getIntent().getSerializableExtra("createdUser");

                    Log.d("fuckgetcUserid1",cUser.getUser_Id());

                    Intent intent=new Intent(uploadcategory.this,Mainpage.class);
                    intent.putExtra("createdUser", cUser);
                    startActivity(intent);
                }

                else{
                    Call<User> call2 = RetrofitClient.getInstance().getMyApi().getOneUser(userId);
                    call2.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call2, Response<User> response2) {
                            User cUser = response2.body();

                            Log.d("fuckgetcUserid2",cUser.getUser_Id());

                            Intent intent=new Intent(uploadcategory.this,Mainpage.class);
                            intent.putExtra("createdUser", cUser);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<User> call2, Throwable t2) {
                            Toast.makeText(getApplicationContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
                        }

                    });
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id =item.getItemId();
        int count=0;
        if(id==R.id.item_done)
        {
            //String itemSelected="Selected items:\n";
            for(int i=0;i<listViewData.getCount();i++)
            {
                if(listViewData.isItemChecked(i)){
                    //itemSelected+=listViewData.getItemAtPosition(i)+"\n";
                    count+=1;

                }
            }
            if(count==0)
            {
                customToastView("1개 이상 선택해주세요");
            } else if (count>5)
            {
                customToastView("5개 이하로 선택해주세요");
            }
            else
            {
                //아직 구현 못함 ㅠㅠ 토스트메시지 띄우고 싶은데
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void customToastView(String text)
    {
        LayoutInflater inflater=getLayoutInflater();
        View layout =inflater.inflate(R.layout.toast_board,(ViewGroup) findViewById((R.id.toast_layout_root)));
        TextView textView=layout.findViewById(R.id.textboard);
        textView.setText(text);
        Toast toastView = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toastView.setView(layout);
        toastView.show();

    }
}