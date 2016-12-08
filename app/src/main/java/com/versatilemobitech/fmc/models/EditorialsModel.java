package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/7/2016.
 */
public class EditorialsModel extends Model {

    private String book_name;
    private String book_path;
    private String book_description;
    private String book_image;
    private String source;
    private String Small_Image;
    private String Big_Image;
    private String book_id;
    private ArrayList<EditorialsModel> editorialsModels;
    private String total_number_of_posts;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_path() {
        return book_path;
    }

    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSmall_Image() {
        return Small_Image;
    }

    public void setSmall_Image(String small_Image) {
        Small_Image = small_Image;
    }

    public String getBig_Image() {
        return Big_Image;
    }

    public void setBig_Image(String big_Image) {
        Big_Image = big_Image;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public ArrayList<EditorialsModel> getEditorialsModels() {
        return editorialsModels;
    }

    public void setEditorialsModels(ArrayList<EditorialsModel> editorialsModels) {
        this.editorialsModels = editorialsModels;
    }

    public String getTotal_number_of_posts() {
        return total_number_of_posts;
    }

    public void setTotal_number_of_posts(String total_number_of_posts) {
        this.total_number_of_posts = total_number_of_posts;
    }
}
