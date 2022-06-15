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

import com.example.recommentflowchartui.DTO.Post;

public class upload_page extends AppCompatActivity {
    private Button category_bt;
    private EditText link,title,review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_page);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        link=(EditText)findViewById(R.id.uploadlink);
        title=(EditText)findViewById(R.id.uploadtitle);
        review=(EditText)findViewById(R.id.uploadreview);
        category_bt=findViewById(R.id.btcategory);
        String userId = (String) getIntent().getSerializableExtra("userId");
        category_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1,str2,str3;
                str1=link.getText().toString();
                str2=title.getText().toString();
                str3=review.getText().toString();

                if((str1.length()!=0)&&(str2.length()!=0)&&(str3.length()!=0))
                {
                    Post newPost = new Post();
                    newPost.setUser_Id(userId);
                    newPost.setLink(str1);
                    newPost.setThumbnail(str1);
                    newPost.setTitle(str2);
                    newPost.setContent(str3);
                    Intent intent=new Intent(upload_page.this,uploadcategory.class);
                    intent.putExtra("newPost", newPost);
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