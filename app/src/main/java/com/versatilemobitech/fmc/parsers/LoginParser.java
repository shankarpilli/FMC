package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.LoginModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONObject;

/**
 * Created by Shankar on 11/20/2016.
 */
public class LoginParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        LoginModel mLoginModel = new LoginModel();
        if (response != null) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.has("status") && !jsonObject.opt("status").equals("error")) {
                    mLoginModel.setStatus(true);
                    mLoginModel.setMessage(jsonObject.optString("msg"));
                    mLoginModel.setUser_id(jsonObject.optString("user_id"));
                    mLoginModel.setUser_name(jsonObject.optString("user_name"));
                    mLoginModel.setCompany_name(jsonObject.optString("company_name"));
                    mLoginModel.setKey(jsonObject.optString("key"));
                    mLoginModel.setProfile_pic(jsonObject.optString("profile_pic"));

                    mLoginModel.setFirst_name(jsonObject.optString("first_name"));
                    mLoginModel.setLast_name(jsonObject.optString("last_name"));
                    mLoginModel.setBusiness_mail(jsonObject.optString("business_email_id"));
                    mLoginModel.setPersonal_mail(jsonObject.optString("personal_email_id"));
                    mLoginModel.setContact(jsonObject.optString("contact_number"));
                    mLoginModel.setAlternate(jsonObject.optString("alternate_contact_number"));
                    mLoginModel.setCurrent_location(jsonObject.optString("current_location"));
                    mLoginModel.setInterested_location(jsonObject.optString("interested_location"));

                } else {
                    mLoginModel.setStatus(false);
                }
            } catch (Exception e) {
                mLoginModel.setStatus(false);
            }
        }
        return mLoginModel;
    }
}
