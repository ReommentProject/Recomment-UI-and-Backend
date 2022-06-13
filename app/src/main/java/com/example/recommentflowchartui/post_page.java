package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recommentflowchartui.DTO.Post;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class post_page extends YouTubeBaseActivity {
    private Button comment;
    private ImageView profile, thumbnail;
    private TextView title, review;
    private YouTubePlayerView ytPlayer;

    String api_key = "AIzaSyAgwRo5cX1rkybuFWkGtVIWGRzAumSbeb4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_post_page);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();
        initView();
        setEvent();

        Post post = (Post) getIntent().getSerializableExtra("this_post");
        // 섬네일 작업
        String url = post.getThumbnail();
        String id = url.substring(17);  //맨마지막 '/'뒤에 id가있으므로 그것만 파싱해줌
//        String imgUrl ="https://img.youtube.com/vi/"+ id+ "/" + "maxresdefault.jpg";  //유튜브 썸네일 불러오는 방법
//        Glide.with(this).load(imgUrl).into(thumbnail);
//        thumbnail.setVisibility(View.VISIBLE); //동영상이면 재생버튼도 보이게한다.

        // 타이틀, 리뷰 텍스트 작업
        title.setText(post.getTitle());
        review.setText(post.getContent());

        showYoutube(id);
    }

    private void initView(){
        comment=(Button)findViewById(R.id.postcommment);
        profile=(ImageView)findViewById(R.id.fprofile);
//        thumbnail=(ImageView)findViewById(R.id.image);
        title=(TextView) findViewById(R.id.title);
        review=(TextView) findViewById(R.id.review);
        ytPlayer=(YouTubePlayerView) findViewById(R.id.ytPlayer);
    }

    private void setEvent(){
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(post_page.this,Commentlist.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(post_page.this,FriendPage.class);
                startActivity(intent);
            }
        });
    }

    private void showYoutube(String text){
        ytPlayer.initialize(
                api_key,
                new YouTubePlayer.OnInitializedListener() {
                    // Implement two methods by clicking on red
                    // error bulb inside onInitializationSuccess
                    // method add the video link or the playlist
                    // link that you want to play In here we
                    // also handle the play and pause
                    // functionality
                    @Override
                    public void onInitializationSuccess(
                            YouTubePlayer.Provider provider,
                            YouTubePlayer youTubePlayer, boolean b)
                    {
                        youTubePlayer.loadVideo(text);
                        youTubePlayer.play();
                    }

                    // Inside onInitializationFailure
                    // implement the failure functionality
                    // Here we will show toast
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult
                                                                youTubeInitializationResult)
                    {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}