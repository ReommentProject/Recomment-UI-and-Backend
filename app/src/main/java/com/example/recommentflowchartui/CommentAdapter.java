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

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CustomViewHolder> {


    private ArrayList<CommentData> arrayList;
    private Context mContext;


    public CommentAdapter(ArrayList<CommentData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_commentlist,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.commentprofile.setImageResource(arrayList.get(position).getCommentProfile());
        holder.commentnick.setText(arrayList.get(position).getCommentNick());
        holder.comment.setText(arrayList.get(position).getComment());
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return (null !=arrayList ? arrayList.size():0);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView commentprofile;
        protected TextView commentnick;
        protected TextView comment;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.commentprofile=(ImageView) itemView.findViewById(R.id.commentprofile);
            this.commentnick=(TextView) itemView.findViewById(R.id.commentncik);
            this.comment=(TextView) itemView.findViewById(R.id.comment);

            commentprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos=getAdapterPosition();
                    CommentData commentData=arrayList.get(currentPos);
                    Intent intent=new Intent(mContext,FriendPage.class);
                    mContext.startActivity(intent);

                }
            });
        }
    }
}
