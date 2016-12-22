package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 11/6/2016.
 */
public class HomeDataModel extends Model {
    private String post_text;
    private String post_image;
    private String post_doc;
    private String doc_extension;
    private String user_id;
    private String post_id;
    private String first_name;
    private String last_name;
    private String company_name;
    private String profile_pic;
    private int comments_count;
    private String datetime;

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getPost_doc() {
        return post_doc;
    }

    public void setPost_doc(String post_doc) {
        this.post_doc = post_doc;
    }

    public String getDoc_extension() {
        return doc_extension;
    }

    public void setDoc_extension(String doc_extension) {
        this.doc_extension = doc_extension;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
