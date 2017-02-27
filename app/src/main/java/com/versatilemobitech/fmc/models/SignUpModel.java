package com.versatilemobitech.fmc.models;

import java.io.Serializable;

/**
 * Created by Shankar on 10/25/2016.
 */

public class SignUpModel extends Model implements Serializable {

    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String company_name;
    private String profile_pic;
    private String business_email_id;
    private String personal_email_id;
    private String contact_number;
    private String alternate_contact_number;
    private String current_location;
    private String interested_location;
    private String created_date;
    private String user_id;
    private String modified_date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBusiness_email_id() {
        return business_email_id;
    }

    public void setBusiness_email_id(String business_email_id) {
        this.business_email_id = business_email_id;
    }

    public String getPersonal_email_id() {
        return personal_email_id;
    }

    public void setPersonal_email_id(String personal_email_id) {
        this.personal_email_id = personal_email_id;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAlternate_contact_number() {
        return alternate_contact_number;
    }

    public void setAlternate_contact_number(String alternate_contact_number) {
        this.alternate_contact_number = alternate_contact_number;
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }
}
