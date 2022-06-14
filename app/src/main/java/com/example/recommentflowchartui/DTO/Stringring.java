package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stringring implements Serializable {
    @SerializedName("fuck")
    private String user_id;

    public Stringring(String user_id){
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }
}
