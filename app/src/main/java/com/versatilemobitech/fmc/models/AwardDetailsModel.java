package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 12/23/2016.
 */

public class AwardDetailsModel {
    private String year;
    private String award_name;
    private String award_description;
    private String award_doc;
    private String award_id;
    private String first_name;
    private String last_name;
    private String company_name;
    private String profile_pic;
    private boolean isVisible;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAward_name() {
        return award_name;
    }

    public void setAward_name(String award_name) {
        this.award_name = award_name;
    }

    public String getAward_description() {
        return award_description;
    }

    public void setAward_description(String award_description) {
        this.award_description = award_description;
    }

    public String getAward_doc() {
        return award_doc;
    }

    public void setAward_doc(String award_doc) {
        this.award_doc = award_doc;
    }

    public String getAward_id() {
        return award_id;
    }

    public void setAward_id(String award_id) {
        this.award_id = award_id;
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
