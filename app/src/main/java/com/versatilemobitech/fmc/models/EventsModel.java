package com.versatilemobitech.fmc.models;

/**
 * Created by Rev's Nani on 06-11-2016.
 */

public class EventsModel extends Model {

private String mVenue;
private String mOrganizedBy;
private String mContact;
private String mChiefGuest;
private String mDateOfEvent;
private String mDetails;
private boolean visible;

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

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }

    public String getmChiefGuest() {
        return mChiefGuest;
    }

    public void setmChiefGuest(String mChiefGuest) {
        this.mChiefGuest = mChiefGuest;
    }

    public String getmDateOfEvent() {
        return mDateOfEvent;
    }

    public void setmDateOfEvent(String mDateOfEvent) {
        this.mDateOfEvent = mDateOfEvent;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
