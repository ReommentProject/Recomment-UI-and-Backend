package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Interest implements Serializable {

    @SerializedName("userId")
    private String user_Id;

    @SerializedName("Interests")
    private String singleInterest;


    public Interest(String user_Id, String singleInterest) {
        this.user_Id = user_Id;
        this.singleInterest = singleInterest;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public String getSingleInterest() {
        return singleInterest;
    }
}
