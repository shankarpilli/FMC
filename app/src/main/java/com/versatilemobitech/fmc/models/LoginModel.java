package com.versatilemobitech.fmc.models;

/**
 * Created by Shankar on 11/20/2016.
 */
public class LoginModel extends Model {
    private String user_id;
    private String user_name;
    private String key;
    private String company_name;
    private String profile_pic;

    private String first_name;
    private String last_name;
    private String business_mail;
    private String personal_mail;
    private String contact;
    private String alternate;
    private String current_location;
    private String interested_location;

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

    public String getBusiness_mail() {
        return business_mail;
    }

    public void setBusiness_mail(String business_mail) {
        this.business_mail = business_mail;
    }

    public String getPersonal_mail() {
        return personal_mail;
    }

    public void setPersonal_mail(String personal_mail) {
        this.personal_mail = personal_mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
    }

    public String getInterested_location() {
        return interested_location;
    }

    public void setInterested_location(String interested_location) {
        this.interested_location = interested_location;
    }
}
