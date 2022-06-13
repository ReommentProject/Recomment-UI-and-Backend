package com.example.recommentflowchartui;

public class CommentData {

    private int commentprofile;
    private String commentnick;
    private String comment;

    public CommentData(int commentprofile, String commentnick, String comment) {
        this.commentprofile = commentprofile;
        this.commentnick = commentnick;
        this.comment = comment;
    }

    public int getCommentProfile() {
        return commentprofile;
    }

    public void setCommentProfile(int commentprofile) {
        this.commentprofile = commentprofile;
    }

    public String getCommentNick() {
        return commentnick;
    }

    public void setCommentNick(String commentnick) {
        this.commentnick = commentnick;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

