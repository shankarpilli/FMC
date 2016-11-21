package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/6/2016.
 */
public class GalleryFolderModel extends Model {
    private String total_number_of_posts;

    private String album_name;
    private String album_image_path;
    private String photo_album_id;

    ArrayList<GalleryFolderModel> mList;

    public String getTotal_number_of_posts() {
        return total_number_of_posts;
    }

    public void setTotal_number_of_posts(String total_number_of_posts) {
        this.total_number_of_posts = total_number_of_posts;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_image_path() {
        return album_image_path;
    }

    public void setAlbum_image_path(String album_image_path) {
        this.album_image_path = album_image_path;
    }

    public String getPhoto_album_id() {
        return photo_album_id;
    }

    public void setPhoto_album_id(String photo_album_id) {
        this.photo_album_id = photo_album_id;
    }

    public ArrayList<GalleryFolderModel> getmList() {
        return mList;
    }

    public void setmList(ArrayList<GalleryFolderModel> mList) {
        this.mList = mList;
    }
}
