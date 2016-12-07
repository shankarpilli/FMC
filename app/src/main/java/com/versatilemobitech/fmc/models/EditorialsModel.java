package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/7/2016.
 */
public class EditorialsModel extends Model {

    private String book_name;
    private String book_description;
    private String book_path;
    private String book_id;

    private ArrayList<EditorialsModel> modelArrayList;
    private String total_number_of_posts;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getBook_path() {
        return book_path;
    }

    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public ArrayList<EditorialsModel> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(ArrayList<EditorialsModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    public String getTotal_number_of_posts() {
        return total_number_of_posts;
    }

    public void setTotal_number_of_posts(String total_number_of_posts) {
        this.total_number_of_posts = total_number_of_posts;
    }
}
