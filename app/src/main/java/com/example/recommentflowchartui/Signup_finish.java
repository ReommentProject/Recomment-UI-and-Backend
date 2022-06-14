package com.example.recommentflowchartui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_finish extends AppCompatActivity {

    private Button finish;
    private EditText profile;
    private User cUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup_finish);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        finish = findViewById(R.id.finish);
        profile = findViewById(R.id.profile);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = profile.getText().toString();
                if (str.length() >= 10) {


                    User cUser = (User) getIntent().getSerializableExtra("createdUser");


                    Intent intent = getIntent();
                    cUser.setIntroduce(str);
                    Log.i("fuck", cUser.getNickname());
                    createUser(cUser);

                    customToastView("회원가입 완료");
                    intent = new Intent(Signup_finish.this, Loginpage.class);
                    startActivity(intent);
                } else if (str.length() < 10) {
                    customToastView("10자이상 작성해주세요.");
                }

            }
        });

    }

    public void customToastView(String text) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_board, (ViewGroup) findViewById((R.id.toast_layout_root)));
        TextView textView = layout.findViewById(R.id.textboard);
        textView.setText(text);
        Toast toastView = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toastView.setView(layout);
        toastView.show();

    }

    private void createUser(User cUser) {

        Call<User> call = RetrofitClient.getInstance().getMyApi().createUser(cUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("fuck 회원가입 성공", "" + response.body().getNickname());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("fuck 회원가입 실패", "");
            }
        });
    }
}

//while (i > 0) {
//        Interest temp = new Interest(userId, "test" + i);
//
//        Call<Interest> call = RetrofitClient.getInstance().getMyApi().createInterest(temp);
//        call.enqueue(new Callback<Interest>() {
//@Override
//public void onResponse(Call<Interest> call, Response<Interest> response) {
//        Log.i("fuck", "likes : hmm..." + response.body());
//        }
//
//@Override
//public void onFailure(Call<Interest> call, Throwable t) {
//        Log.i("fuck", "왜 안되지..?");
//        }
//        });
//
//        i--;
//        }


//    String userId = cUser.getUser_Id(); // 회원가입하는 유저 가져오기
//    int i = listViewData.getCount(); // 체크된 개수 구하기
//// 체크 된 개수 만큼 userId에 흥미 추가