package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

public class Intt {
    @SerializedName("id")
    private int post_Id;

    public Intt(int post_Id){
        this.post_Id = post_Id;
    }

    public int getPost_Id() {
        return post_Id;
    }
}
