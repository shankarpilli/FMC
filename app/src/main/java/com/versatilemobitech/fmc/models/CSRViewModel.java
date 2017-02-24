package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/6/2016.
 */
public class CSRViewModel extends Model {
    private String total_number_of_posts;

    private String image_path;
    private String image_title;
    private String image_description;
    private String csr_id;
    private String datetime;

    ArrayList<CSRViewModel> mList;

    public String getTotal_number_of_posts() {
        return total_number_of_posts;
    }

    public void setTotal_number_of_posts(String total_number_of_posts) {
        this.total_number_of_posts = total_number_of_posts;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

    public String getImage_description() {
        return image_description;
    }

    public void setImage_description(String image_description) {
        this.image_description = image_description;
    }

    public String getCsr_id() {
        return csr_id;
    }

    public void setCsr_id(String csr_id) {
        this.csr_id = csr_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ArrayList<CSRViewModel> getmList() {
        return mList;
    }

    public void setmList(ArrayList<CSRViewModel> mList) {
        this.mList = mList;
    }
}