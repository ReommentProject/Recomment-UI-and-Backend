package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

    @SerializedName("id")
    private int post_Id;

    @SerializedName("userId")
    private String user_Id;

    @SerializedName("likes")
    private int likes;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("link")
    private String link;

    @SerializedName("interest")
    private String interest;

    @SerializedName("createdAt")
    private String createdTime;

    @SerializedName("updatedAt")
    private String updatedTime;


    public Post(int post_Id, String user_Id, int likes, String title, String content, String thumbnail, String link, String createdTime, String updatedTime) {
        this.post_Id = post_Id;
        this.user_Id = user_Id;
        this.likes = likes;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.link = link;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Post(int post_Id, String user_Id, int likes, String title, String content, String thumbnail, String link, String interest, String createdTime, String updatedTime) {
        this.post_Id = post_Id;
        this.user_Id = user_Id;
        this.likes = likes;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.link = link;
        this.interest = interest;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Post() {
        this.likes = 0;
    }

    public int getPost_Id() {
        return post_Id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public int getLikes() {
        return likes;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLink() {
        return link;
    }

    public String getInterest() {
        return interest;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }


    public void setPost_Id(int post_Id) {
        this.post_Id = post_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}