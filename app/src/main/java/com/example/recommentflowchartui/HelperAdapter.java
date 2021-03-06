package com.example.recommentflowchartui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recommentflowchartui.DTO.Intt;
import com.example.recommentflowchartui.DTO.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HelperAdapter extends RecyclerView.Adapter {
    Context context;
    String userId;
    List<Post> postList;
    String category;

    public HelperAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    public HelperAdapter(Context context, List<Post> postList, String userId) {
        this.context = context;
        this.postList = postList;
        this.userId = userId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_item,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        viewHolderClass.textView1.setText(postList.get(position).getTitle());
        viewHolderClass.textView2.setText("> "+ postList.get(position).getContent());
        viewHolderClass.writer.setText(postList.get(position).getUser_Id());
        viewHolderClass.likes.setText("?????????: "+Integer.toString(postList.get(position).getLikes())+"");



        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // ?????? ?????? ????????????
            String temp = postList.get(position).getCreatedTime();
            Log.i("fucking created time : ", ""+temp);
            String temp2 = temp.substring(0, 10);
            String temp3 = temp.substring(11, 19);
            String temp4 = temp2 + " " + temp3;

            Date writtenTime = formatter.parse(temp4);

            long Wtime = writtenTime.getTime();
            long Ntime = System.currentTimeMillis();
            Log.i("fucking created time : ", ""+formatter.format(Ntime));

            long second = 1000;
            long minute = second * 60;
            long hour = minute * 60;
            long date = hour * 24;
            long week = date * 7;
            long month = week * 4;
            long year = month * 12;

            long fuckfuck = Ntime-Wtime-(9*hour);

            if(fuckfuck < minute){
                viewHolderClass.writtenAt.setText((fuckfuck/second) + "??????");
            } else if (fuckfuck < hour){
                viewHolderClass.writtenAt.setText((fuckfuck/minute) + "??????");
            } else if (fuckfuck < date){
                viewHolderClass.writtenAt.setText((fuckfuck/hour) + "?????????");
            } else if (fuckfuck < week){
                viewHolderClass.writtenAt.setText((fuckfuck/date) + "??????");
            } else if (fuckfuck < month){
                viewHolderClass.writtenAt.setText((fuckfuck/week) + "??????");
            } else if (fuckfuck < year){
                viewHolderClass.writtenAt.setText((fuckfuck/month) + "??????");
            } else {
                viewHolderClass.writtenAt.setText((fuckfuck/year) + "??????");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String url= postList.get(position).getThumbnail();
        String url2="https://youtu.be/JNL44p5kzTk";

        String id = url.substring(17);  //???????????? '/'?????? id??????????????? ????????? ????????????
        String imgUrl ="https://img.youtube.com/vi/"+ id+ "/" + "maxresdefault.jpg";  //????????? ????????? ???????????? ??????
        Glide.with(context).load(imgUrl).into(viewHolderClass.imageView);
        viewHolderClass.imageView.setVisibility(View.VISIBLE); //??????????????? ??????????????? ???????????????.

        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Item Selected",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context,post_page.class);
                intent.putExtra("this_post", postList.get(position));
                intent.putExtra("userId", userId);
                context.startActivity(intent);
            }
        });

        ScaleAnimation scaleAnimation;
        BounceInterpolator bounceInterpolator;//?????????????????? ???????????? ????????? ??????, ????????? ??????????????? ????????? ???????????? ????????? ?????? ??? ??? ??????

        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);

        scaleAnimation.setDuration(500);
        bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        viewHolderClass.likeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    increaseLikes(postList.get(position).getPost_Id());
                    viewHolderClass.likes.setText("?????????: " + Integer.toString(postList.get(position).getLikes()+1) + "");
                }else{
                    decreaseLikes(postList.get(position).getPost_Id());
                    viewHolderClass.likes.setText("?????????: " + Integer.toString(postList.get(position).getLikes()) + "");
                }
                compoundButton.startAnimation(scaleAnimation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder
    {
        TextView textView1, textView2, writer, likes, writtenAt;
        ImageView imageView;
        CompoundButton likeBtn;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView1=(TextView)itemView.findViewById(R.id.textView1);
            textView2=(TextView)itemView.findViewById(R.id.textView2);
            writer = (TextView)itemView.findViewById(R.id.writer);
            likes = (TextView)itemView.findViewById(R.id.likes);
            writtenAt = (TextView)itemView.findViewById(R.id.writtenAt);
            imageView=(ImageView) itemView.findViewById(R.id.imageview);
            likeBtn=(CompoundButton) itemView.findViewById(R.id.likeBtn);
        }
    }

    public void increaseLikes(int fuckingId){
        Intt temp = new Intt(fuckingId);
        Call<Post> call = RetrofitClient.getInstance().getMyApi().upLike(temp);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("fuck", "likes : hmm..." + response.body().getLikes());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("fuck", "??? ?????????..?");
            }
        });
    }

    public void decreaseLikes(int fuckingId){
        Intt temp = new Intt(fuckingId);
        Call<Post> call = RetrofitClient.getInstance().getMyApi().downLike(temp);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("fuck", "likes : hmm..."  + response.body().getLikes());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("fuck", "??? ?????????..?");
            }
        });
    }
}
