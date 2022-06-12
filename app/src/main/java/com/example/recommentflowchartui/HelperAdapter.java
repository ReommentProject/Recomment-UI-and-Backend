package com.example.recommentflowchartui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter {
    Context context;
    List<Content> contentList;

    public HelperAdapter(Context context, List<Content> contentList) {
        this.context = context;
        this.contentList = contentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_item,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        viewHolderClass.textView1.setText(contentList.get(position).getTitle());
        viewHolderClass.textView2.setText("> "+contentList.get(position).getContent());
        viewHolderClass.writer.setText(contentList.get(position).getUser_Id());
        viewHolderClass.likes.setText("좋아영: "+Integer.toString(contentList.get(position).getLikes())+"");



        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 작성 시간 불러내기
            String temp = contentList.get(position).getCreatedTime();
            Log.i("fucking created time : ", ""+temp);
            String temp2 = temp.substring(0, 10);
            String temp3 = temp.substring(11, 19);
            String temp4 = temp2 + " " + temp3;

            Date writtenTime = formatter.parse(temp4);

            long Wtime = writtenTime.getTime();
            long Ntime = System.currentTimeMillis();

            long second = 1000;
            long minute = second * 60;
            long hour = minute * 60;
            long date = hour * 24;
            long week = date * 7;
            long month = week * 4;
            long year = month * 12;

            long fuckfuck = Ntime-Wtime-(9*hour);

            if(fuckfuck < minute){
                viewHolderClass.writtenAt.setText((fuckfuck/second) + "초전");
            } else if (fuckfuck < hour){
                viewHolderClass.writtenAt.setText((fuckfuck/minute) + "분전");
            } else if (fuckfuck < date){
                viewHolderClass.writtenAt.setText((fuckfuck/hour) + "시간전");
            } else if (fuckfuck < week){
                viewHolderClass.writtenAt.setText((fuckfuck/date) + "일전");
            } else if (fuckfuck < month){
                viewHolderClass.writtenAt.setText((fuckfuck/week) + "주전");
            } else if (fuckfuck < year){
                viewHolderClass.writtenAt.setText((fuckfuck/month) + "달전");
            } else {
                viewHolderClass.writtenAt.setText((fuckfuck/year) + "년전");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String url=contentList.get(position).getThumbnail();
        String url2="https://youtu.be/JNL44p5kzTk";

        String id = url.substring(17);  //맨마지막 '/'뒤에 id가있으므로 그것만 파싱해줌
        String imgUrl ="https://img.youtube.com/vi/"+ id+ "/" + "maxresdefault.jpg";  //유튜브 썸네일 불러오는 방법
        Glide.with(context).load(imgUrl).into(viewHolderClass.imageView);
        viewHolderClass.imageView.setVisibility(View.VISIBLE); //동영상이면 재생버튼도 보이게한다.

        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Item Selected",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder
    {
        TextView textView1, textView2, writer, likes, writtenAt;
        ImageView imageView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView1=(TextView)itemView.findViewById(R.id.textView1);
            textView2=(TextView)itemView.findViewById(R.id.textView2);
            writer = (TextView)itemView.findViewById(R.id.writer);
            likes = (TextView)itemView.findViewById(R.id.likes);
            writtenAt = (TextView)itemView.findViewById(R.id.writtenAt);
            imageView=(ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}
