package com.example.recommentflowchartui.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Intt2 implements Serializable {
    @SerializedName("hmm")
    private int post_Id;

    public Intt2(int post_Id){
        this.post_Id = post_Id;
    }

    public int getPost_Id() {
        return post_Id;
    }
}
