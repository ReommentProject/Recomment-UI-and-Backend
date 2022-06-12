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
        viewHolderClass.textView2.setText(contentList.get(position).getContent());

        String url2=contentList.get(position).getThumbnail();
        String url="https://youtu.be/JNL44p5kzTk";
        String id = url.substring(17);  //맨마지막 '/'뒤에 id가있으므로 그것만 파싱해줌
        String imgUrl ="https://img.youtube.com/vi/"+ id+ "/" + "default.jpg";  //유튜브 썸네일 불러오는 방법
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
        TextView textView1, textView2;
        ImageView imageView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView1=(TextView)itemView.findViewById(R.id.textView1);
            textView2=(TextView)itemView.findViewById(R.id.textView2);
            imageView=(ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}
