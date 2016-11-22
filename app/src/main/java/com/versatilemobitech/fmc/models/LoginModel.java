package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 11/20/2016.
 */
public class LoginModel extends Model {
    private String user_id;
    private String user_name;
    private String key;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
