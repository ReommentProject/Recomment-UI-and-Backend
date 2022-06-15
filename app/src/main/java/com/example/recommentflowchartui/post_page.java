package com.example.recommentflowchartui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommentflowchartui.DTO.Comment;
import com.example.recommentflowchartui.DTO.Intt;
import com.example.recommentflowchartui.DTO.Intt2;
import com.example.recommentflowchartui.DTO.Post;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class post_page extends YouTubeBaseActivity {
    private Button comment;
    private ImageView profile;
    private TextView title, review, name;
    private YouTubePlayerView ytPlayer;
    private CompoundButton likeBtn;
    private ArrayList<CommentData> arrayList;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText commentLine;
    private Post allRangePost;
    private String allRangeUserId;

    String api_key = "AIzaSyAgwRo5cX1rkybuFWkGtVIWGRzAumSbeb4";
    List<Comment> commentList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_post_page);

        initView();
        setEvent();

        Post post = (Post) getIntent().getSerializableExtra("this_post");
        allRangePost = post;

        String userId = getIntent().getSerializableExtra("userId").toString();
        allRangeUserId = userId;
        // 섬네일 작업
        String url = post.getThumbnail();
        String id = url.substring(17);  //맨마지막 '/'뒤에 id가있으므로 그것만 파싱해줌

        // 타이틀, 리뷰 텍스트 작업
        title.setText(post.getTitle());
        review.setText(post.getContent());
        name.setText(post.getUser_Id());

        showYoutube(id);
        setLikeBtnAnimation(post);
        setComments(post);

    }

    private void initView() {
        comment = (Button) findViewById(R.id.postcommment);
        profile = (ImageView) findViewById(R.id.fprofile);
//        thumbnail=(ImageView)findViewById(R.id.image);
        title = (TextView) findViewById(R.id.title);
        review = (TextView) findViewById(R.id.review);
        ytPlayer = (YouTubePlayerView) findViewById(R.id.ytPlayer);
        likeBtn = (CompoundButton) findViewById(R.id.likeBtn);
        name = (TextView) findViewById(R.id.name);
        commentLine = (EditText) findViewById(R.id.commentLine);

        recyclerView = (RecyclerView) findViewById(R.id.commentlist);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
    }

    private void setEvent() {
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentString = commentLine.getText().toString();
                Comment temp = new Comment(allRangeUserId, allRangePost.getPost_Id(), commentString);

                Call<Comment> call = RetrofitClient.getInstance().getMyApi().createComment(temp);
                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        CommentData commentData = new CommentData(R.drawable.mypage, allRangeUserId, temp.getContents());
                        Log.i("fuck", "comments : hmm..." + response.body());
                        arrayList.add(commentData);
                        commentAdapter = new CommentAdapter(arrayList);
                        recyclerView.setAdapter(commentAdapter);
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Log.i("fuck", "왜 안되지..?");
                    }
                });
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(post_page.this, FriendPage.class);
                startActivity(intent);
            }
        });
    }

    private void setLikeBtnAnimation(Post post) {
        ScaleAnimation scaleAnimation;
        BounceInterpolator bounceInterpolator;//애니메이션이 일어나는 동안의 회수, 속도를 조절하거나 시작과 종료시의 효과를 추가 할 수 있다

        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);

        scaleAnimation.setDuration(500);
        bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);


        likeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    increaseLikes(post.getPost_Id());
                } else {
                    decreaseLikes(post.getPost_Id());
                }
                compoundButton.startAnimation(scaleAnimation);
            }
        });
    }

    private void showYoutube(String text) {
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
                            YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo(text);
                        youTubePlayer.play();
                    }

                    // Inside onInitializationFailure
                    // implement the failure functionality
                    // Here we will show toast
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult
                                                                youTubeInitializationResult) {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void increaseLikes(int fuckingId) {
        Intt temp = new Intt(fuckingId);
        Call<Post> call = RetrofitClient.getInstance().getMyApi().upLike(temp);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("fuck", "likes : hmm..." + response.body().getLikes());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("fuck", "왜 안되지..?");
            }
        });
    }

    public void decreaseLikes(int fuckingId) {
        Intt temp = new Intt(fuckingId);
        Call<Post> call = RetrofitClient.getInstance().getMyApi().downLike(temp);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("fuck", "likes : hmm..." + response.body().getLikes());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("fuck", "왜 안되지..?");
            }
        });
    }

    public void setComments(Post post) {
        // 댓글창 작업

        Intt2 temp = new Intt2(post.getPost_Id());

        Log.i("fuck", "post id : " + temp.getPost_Id());

        Call<List<Comment>> call = RetrofitClient.getInstance().getMyApi().getCommentByPostId(temp);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                commentList = response.body();

                for (int i = 0; i < commentList.size(); i++) {
                    CommentData commentData = new CommentData(R.drawable.mypage, commentList.get(i).getUser_Id(), commentList.get(i).getContents());
                    Log.i("fuck", "comment contents : "+ commentList.get(i).getUser_Id());
                    arrayList.add(commentData);
                }
                commentAdapter = new CommentAdapter(arrayList);
                recyclerView.setAdapter(commentAdapter);

                Log.i("fuck", "comments : hmm..." + response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.i("fuck", "왜 안되지..?");
            }
        });
    }
}