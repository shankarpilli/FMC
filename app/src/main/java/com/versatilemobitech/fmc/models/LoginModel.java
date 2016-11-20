package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 11/20/2016.
 */
public class LoginModel extends Model {
    private String user_id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
