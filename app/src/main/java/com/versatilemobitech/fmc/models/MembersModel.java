package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/7/2016.
 */
public class MembersModel extends Model {
    private String mName;
    private String last_name;
    private String mCompany;
    private String user_id;
    private String mImage;
    private ArrayList<MembersModel> membersModels;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public ArrayList<MembersModel> getMembersModels() {
        return membersModels;
    }

    public void setMembersModels(ArrayList<MembersModel> membersModels) {
        this.membersModels = membersModels;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
