package com.example.recommentflowchartui;

public class friendData {

    private int fprofile;
    private String name;
    private String content;
    private String owner;

    public friendData(int fprofile, String name, String content, String owner) {
        this.fprofile = fprofile;
        this.name = name;
        this.content = content;
        this.owner = owner;
    }
    public friendData(int fprofile, String name) {
        this.fprofile = fprofile;
        this.name = name;
        this.content = "아직모름";
    }

    public int getFprofile() {
        return fprofile;
    }

    public void setFprofile(int fprofile) {
        this.fprofile = fprofile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwner() { return owner; }
}
