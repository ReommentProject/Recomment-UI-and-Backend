package com.example.recommentflowchartui;

public class CategoryVideoData {

    private int videoimg;
    private String videotitle;
    private String videoreview;

    public CategoryVideoData(int videoimg, String videotitle, String videoreview) {
        this.videoimg = videoimg;
        this.videotitle = videotitle;
        this.videoreview = videoreview;
    }

    public int getVideoimg() {
        return videoimg;
    }

    public void setVideoimg(int videoimg) {
        this.videoimg = videoimg;
    }

    public String getVideotitle() {
        return videotitle;
    }

    public void setVideotitle(String videotitle) {
        this.videotitle = videotitle;
    }

    public String getVideoreview() {
        return videoreview;
    }

    public void setVideoreview(String content) {
        this.videoreview = videoreview;
    }
}
