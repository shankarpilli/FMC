package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 12/9/2016.
 */

public class PostDataModel extends Model{
    private String user_id;
    private String post_text;
    private String post_image;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }
}
