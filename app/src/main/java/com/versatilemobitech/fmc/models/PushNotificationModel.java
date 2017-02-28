package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 2/28/2017.
 */

public class PushNotificationModel extends Model {
    private String device_id;
    private String device_os;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }
}
