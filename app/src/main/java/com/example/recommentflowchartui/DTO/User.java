package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    private String user_Id;

    @SerializedName("password")
    private String user_Pwd;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("introduce")
    private String introduce;

    @SerializedName("gender")
    private Boolean gender;

    @SerializedName("profile_Image")
    private String profile_Image;


    public User(String user_Id, String user_Pwd, String nickname, String introduce, Boolean gender, String profile_Image) {
        this.user_Id = user_Id;
        this.user_Pwd = user_Pwd;
        this.nickname = nickname;
        this.introduce = introduce;
        this.gender = gender;
        this.profile_Image = profile_Image;
    }


    public User(String user_Id, String user_Pwd, String nickname) {
        this.user_Id = user_Id;
        this.user_Pwd = user_Pwd;
        this.nickname = nickname;
        this.introduce = "";
        this.gender = true;
        this.profile_Image = "";
    }

    public User() {
        this.user_Id = "";
        this.user_Pwd = "";
        this.nickname = "";
        this.introduce = "";
        this.gender = true;
        this.profile_Image = "";
    }

    public String getUser_Id() {
        return user_Id;
    }

    public String getUser_Pwd() {
        return user_Pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public String getIntroduce() {
        return introduce;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getProfile_Image() {
        return profile_Image;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public void setUser_Pwd(String user_Pwd) {
        this.user_Pwd = user_Pwd;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setProfile_Image(String profile_Image) {
        this.profile_Image = profile_Image;
    }
}