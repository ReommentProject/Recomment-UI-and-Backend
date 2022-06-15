package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comment implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("userId")
    private String user_Id;

    @SerializedName("postId")
    private int post_Id;

    @SerializedName("content")
    private String contents;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    public Comment(String user_Id, int post_Id, String contents) {
        this.user_Id = user_Id;
        this.post_Id = post_Id;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public int getPost_Id() {
        return post_Id;
    }

    public String getContents() {
        return contents;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
