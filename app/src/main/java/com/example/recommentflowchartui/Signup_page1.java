package com.example.recommentflowchartui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recommentflowchartui.DTO.User;

public class Signup_page1 extends AppCompatActivity {
    private Button next;
    private EditText id,password,nickname;

    public User createdUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup_page1);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        next=findViewById(R.id.next);
        id=findViewById(R.id.id);
        password=findViewById(R.id.password);
        nickname=findViewById(R.id.nickname);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1,str2,str3;
                str1=id.getText().toString();
                str2=password.getText().toString();
                str3=nickname.getText().toString();

                if((str1.length()!=0)&&(str2.length()!=0)&&(str3.length()!=0))
                {
                    createdUser = new User(str1, str2, str3);

                    Intent intent=new Intent(Signup_page1.this,Signup_page2.class);
                    intent.putExtra("createdUser", createdUser);
                    startActivity(intent);
                }
                else
                {
                    customToastView("작성하지 않은 정보가 있습니다");
                }
            }
        });
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