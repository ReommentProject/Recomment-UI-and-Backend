package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Friend implements Serializable {

    @SerializedName("userId")
    private String user_Id;

    @SerializedName("friendId")
    private String friend_Id;


    public Friend(String user_Id, String friend_Id) {
        this.user_Id = user_Id;
        this.friend_Id = friend_Id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getFriend_Id() {
        return friend_Id;
    }

    public void setFriend_Id(String friend_Id) {
        this.friend_Id = friend_Id;
    }
}