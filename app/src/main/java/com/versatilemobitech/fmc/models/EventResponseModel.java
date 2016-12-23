package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 12/23/2016.
 */

public class EventResponseModel extends Model {
    private String event_id;
    private String follower_id;
    private String response_text;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(String follower_id) {
        this.follower_id = follower_id;
    }

    public String getResponse_text() {
        return response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }
}
