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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CustomViewHolder> {


    private ArrayList<CategoryData> arrayList;
    private Context mContext;


    public CategoryAdapter(ArrayList<CategoryData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CustomViewHolder holder, int position) {
        holder.star.setImageResource(arrayList.get(position).getStar());
        holder.cname.setText(arrayList.get(position).getName());
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return (null !=arrayList ? arrayList.size():0);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView star;
        protected TextView cname;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.star=(ImageView) itemView.findViewById(R.id.star);
            this.cname=(TextView) itemView.findViewById(R.id.cname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos=getAdapterPosition();
                    CategoryData categoryData=arrayList.get(currentPos);
                    Intent intent=new Intent(mContext,CategoryVideoList.class);
                    //intent.putExtra("userId", userId);
                    mContext.startActivity(intent);


                }
            });
        }
    }
}
