package com.example.recommentflowchartui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommentflowchartui.DTO.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.CustomViewHolder> {


    private List<friendData> arrayList;
    private Context mContext;
    private String friendNick;


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
                    Log.d("fuckgetname",friendData.getName());

                    Call<User> call = RetrofitClient.getInstance().getMyApi().getOneUser(friendData.getName());
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            User friend = response.body();
                            Log.d("fuckFriendNick", friend.getNickname());
                            friendNick=friend.getNickname();
                            Log.d("fuckFriendNick2", friendNick);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(mContext, "An error has occured in get", Toast.LENGTH_LONG).show();
                        }

                    });

                    Intent intent = new Intent(mContext, FriendPage.class);
                    Log.d("fuck owner", friendData.getOwner());

                    intent.putExtra("userId", friendData.getOwner());
                    intent.putExtra("friendId", friendData.getName());
                    Log.d("fuckthisisnick", friendNick);
                    intent.putExtra("friendNick", friendNick);

                    mContext.startActivity(intent);
                }
            });


            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPos=getAdapterPosition();
                    CategoryData categoryData=arrayList.get(currentPos);
                    Intent intent=new Intent(mContext,CategoryVideoList.class);
                    intent.putExtra("category", categoryData.getName());
                    intent.putExtra("userId", categoryData.getOwner());
                    mContext.startActivity(intent);


                }
            });

             */
        }
    }
}
