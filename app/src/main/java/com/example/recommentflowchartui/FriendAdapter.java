package com.example.recommentflowchartui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.CustomViewHolder> {


    private List<friendData> arrayList;
    private Context mContext;


    public FriendAdapter(List<friendData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FriendAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friendlist, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.CustomViewHolder holder, int position) {
        holder.fprofile.setImageResource(arrayList.get(position).getFprofile());
        holder.name.setText(arrayList.get(position).getName());
        holder.content.setText(arrayList.get(position).getContent());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView fprofile;
        protected TextView name;
        protected TextView content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fprofile = (ImageView) itemView.findViewById(R.id.fprofile);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.content = (TextView) itemView.findViewById(R.id.content);
            fprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos = getAdapterPosition();
                    friendData friendData = arrayList.get(currentPos);
                    Intent intent = new Intent(mContext, FriendPage.class);

                    intent.putExtra("userId", friendData.getOwner());
                    intent.putExtra("friendId", friendData.getName());

                    mContext.startActivity(intent);
                }
            });
        }
    }
}
