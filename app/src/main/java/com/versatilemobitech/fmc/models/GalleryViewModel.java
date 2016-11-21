package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/6/2016.
 */
public class GalleryViewModel extends Model {
    private String total_number_of_posts;

    private String image_path;
    private String photo_id;

    ArrayList<GalleryViewModel> mList;

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

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public ArrayList<GalleryViewModel> getmList() {
        return mList;
    }

    public void setmList(ArrayList<GalleryViewModel> mList) {
        this.mList = mList;
    }
}