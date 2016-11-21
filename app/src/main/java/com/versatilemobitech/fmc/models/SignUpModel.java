package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 10/25/2016.
 */

public class SignUpModel extends Model {
    private String name;
    private int icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
