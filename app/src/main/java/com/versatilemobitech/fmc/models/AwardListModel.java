package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 12/23/2016.
 */

public class AwardListModel extends Model {
    private ArrayList<AwardDetailsModel> mAwardListModel;
    private String total_number_of_posts;

    public ArrayList<AwardDetailsModel> getmAwardListModel() {
        return mAwardListModel;
    }

    public void setmAwardListModel(ArrayList<AwardDetailsModel> mAwardListModel) {
        this.mAwardListModel = mAwardListModel;
    }

    public String getTotal_number_of_posts() {
        return total_number_of_posts;
    }

    public void setTotal_number_of_posts(String total_number_of_posts) {
        this.total_number_of_posts = total_number_of_posts;
    }
}
