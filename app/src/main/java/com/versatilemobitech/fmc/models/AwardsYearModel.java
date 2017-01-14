package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 1/14/2017.
 */
public class AwardsYearModel extends Model {
    private String year;
    private ArrayList<AwardsYearModel> awardsYearModels;

    public ArrayList<AwardsYearModel> getAwardsYearModels() {
        return awardsYearModels;
    }

    public void setAwardsYearModels(ArrayList<AwardsYearModel> awardsYearModels) {
        this.awardsYearModels = awardsYearModels;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}