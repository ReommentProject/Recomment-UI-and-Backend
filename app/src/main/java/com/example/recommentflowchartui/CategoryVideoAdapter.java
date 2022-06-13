package com.example.recommentflowchartui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryVideoAdapter extends RecyclerView.Adapter<CategoryVideoAdapter.CustomViewHolder> {


    private ArrayList<CategoryVideoData> arrayList;
    private Context mContext;


    public CategoryVideoAdapter(ArrayList<CategoryVideoData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoryvideo,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.videoimg.setImageResource(arrayList.get(position).getVideoimg());
        holder.videotitle.setText(arrayList.get(position).getVideotitle());
        holder.videoreview.setText(arrayList.get(position).getVideoreview());
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return (null !=arrayList ? arrayList.size():0);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView videoimg;
        protected TextView videotitle;
        protected TextView videoreview;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.videoimg=(ImageView) itemView.findViewById(R.id.videoimg);
            this.videotitle=(TextView) itemView.findViewById(R.id.videotitle);
            this.videoreview=(TextView) itemView.findViewById(R.id.videoreview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos=getAdapterPosition();
                    CategoryVideoData categoryVideoData=arrayList.get(currentPos);
                    Intent intent=new Intent(mContext,post_page.class);
                    mContext.startActivity(intent);

                }
            });
        }
    }
}

