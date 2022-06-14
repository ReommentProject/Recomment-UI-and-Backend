package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recommentflowchartui.DTO.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginpage extends AppCompatActivity {

    private Button Signup;
    private Button Login;
    private EditText Id;
    private EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loginpage);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        Signup=findViewById(R.id.Signup);
        Login=findViewById(R.id.login);
        Id = findViewById(R.id.ID);
        Password = findViewById(R.id.Password);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Loginpage.this,Signup_page1.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });

    }



    private void checkUser() {

        Call<List<User>> call = RetrofitClient.getInstance().getMyApi().getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                boolean rightInput=false;

                List<User> userList = response.body();

                for(int i=0;i<userList.size();i++){
                    String DBId = userList.get(i).getUser_Id();
                    String DBPwd = userList.get(i).getUser_Pwd();
                    String GetId = Id.getText().toString();
                    String GetPwd = Password.getText().toString();

                    if(GetId.equals(DBId)&&GetPwd.equals(DBPwd)){
                        Intent intent=new Intent(Loginpage.this,Mainpage.class);
                        intent.putExtra("userId", GetId);
                        startActivity(intent);
                        rightInput=true;
                        break;
                    }
                }

                if(!rightInput){
                    Toast.makeText(Loginpage.this, "아이디 혹은 비밀번호가 일치하지 않습니다.ㅕ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(Loginpage.this, "An error has occured in get", Toast.LENGTH_LONG).show();
                Log.i("fuck", t+"");
            }

        });

    }
}