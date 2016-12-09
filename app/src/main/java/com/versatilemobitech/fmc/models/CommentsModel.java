package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 12/9/2016.
 */

public class CommentsModel extends Model {
    private String user_id;
    private String post_id;
    private String comment;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
