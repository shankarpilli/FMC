package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 06-11-2016.
 */

public class EventsModel extends Model {


private String mTotalNumberOfPosts;
private String mEventTitle;
private String mVenue;
private String mOrganizedBy;
private String mContactPersonName;
private String mContactInfo;
private String mEventDateTime;
private String mPostedDate;
private String mFullDetails;
private int mResponse;
private boolean visible;

    ArrayList<EventsModel> mEventsModelList;

    public String getmTotalNumberOfPosts() {
        return mTotalNumberOfPosts;
    }

    public void setmTotalNumberOfPosts(String mTotalNumberOfPosts) {
        this.mTotalNumberOfPosts = mTotalNumberOfPosts;
    }

    public String getmEventTitle() {
        return mEventTitle;
    }

    public void setmEventTitle(String mEventTitle) {
        this.mEventTitle = mEventTitle;
    }

    public String getmVenue() {
        return mVenue;
    }

    public void setmVenue(String mVenue) {
        this.mVenue = mVenue;
    }

    public String getmOrganizedBy() {
        return mOrganizedBy;
    }

    public void setmOrganizedBy(String mOrganizedBy) {
        this.mOrganizedBy = mOrganizedBy;
    }

    public String getmContactPersonName() {
        return mContactPersonName;
    }

    public void setmContactPersonName(String mContactPersonName) {
        this.mContactPersonName = mContactPersonName;
    }

    public String getmContactInfo() {
        return mContactInfo;
    }

    public void setmContactInfo(String mContactInfo) {
        this.mContactInfo = mContactInfo;
    }

    public String getmEventDateTime() {
        return mEventDateTime;
    }

    public void setmEventDateTime(String mEventDateTime) {
        this.mEventDateTime = mEventDateTime;
    }

    public String getmPostedDate() {
        return mPostedDate;
    }

    public void setmPostedDate(String mPostedDate) {
        this.mPostedDate = mPostedDate;
    }

    public String getmFullDetails() {
        return mFullDetails;
    }

    public void setmFullDetails(String mFullDetails) {
        this.mFullDetails = mFullDetails;
    }

    public int getmResponse() {
        return mResponse;
    }

    public void setmResponse(int mResponse) {
        this.mResponse = mResponse;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ArrayList<EventsModel> getmEventsModelList() {
        return mEventsModelList;
    }

    public void setmEventsModelList(ArrayList<EventsModel> mEventsModelList) {
        this.mEventsModelList = mEventsModelList;
    }
}
