package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 06-11-2016.
 */

public class EventsModel extends Model {


private String mTotalNumberOfPosts;
private String event_title;
private String venue;
private String organized_by;
private String contact;
private String chief_guest;
private String date_of_event;
private String details;
private String event_id;
private String datetime;
private int response;
private boolean visible;

    ArrayList<EventsModel> mEventsModelList;

    public String getmTotalNumberOfPosts() {
        return mTotalNumberOfPosts;
    }

    public void setmTotalNumberOfPosts(String mTotalNumberOfPosts) {
        this.mTotalNumberOfPosts = mTotalNumberOfPosts;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getOrganized_by() {
        return organized_by;
    }

    public void setOrganized_by(String organized_by) {
        this.organized_by = organized_by;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getChief_guest() {
        return chief_guest;
    }

    public void setChief_guest(String chief_guest) {
        this.chief_guest = chief_guest;
    }

    public String getDate_of_event() {
        return date_of_event;
    }

    public void setDate_of_event(String date_of_event) {
        this.date_of_event = date_of_event;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
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
