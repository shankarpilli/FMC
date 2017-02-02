package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.SignUpModel;

import org.json.JSONObject;

/**
 * Created by Shankar on 11/20/2016.
 */
public class SignUpParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        SignUpModel mSignUpModel = new SignUpModel();
        if (response != null) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String mStatus  = jsonObject.getString("status");

                if (mStatus.equalsIgnoreCase("false")) {
                    mSignUpModel.setStatus(false);
                    mSignUpModel.setMessage(jsonObject.optString("message"));
                } else {
                    mSignUpModel.setStatus(true);
                    mSignUpModel.setMessage(jsonObject.optString("success"));
                    mSignUpModel.setUsername(jsonObject.optString("username"));
                    mSignUpModel.setPassword(jsonObject.optString("password"));
                    mSignUpModel.setFirst_name(jsonObject.optString("first_name"));
                    mSignUpModel.setLast_name(jsonObject.optString("last_name"));
                    mSignUpModel.setCompany_name(jsonObject.optString("company_name"));
                    mSignUpModel.setProfile_pic(jsonObject.optString("profile_pic"));
                    mSignUpModel.setBusiness_email_id(jsonObject.optString("business_email_id"));
                    mSignUpModel.setPersonal_email_id(jsonObject.optString("personal_email_id"));
                    mSignUpModel.setContact_number(jsonObject.optString("contact_number"));
                    mSignUpModel.setAlternate_contact_number(jsonObject.optString("alternate_contact_number"));
                    mSignUpModel.setCurrent_location(jsonObject.optString("current_location"));
                    mSignUpModel.setInterested_location(jsonObject.optString("interested_location"));
                    mSignUpModel.setCreated_date(jsonObject.optString("created_date"));
                    mSignUpModel.setUser_id(jsonObject.optString("user_id"));
                    mSignUpModel.setModified_date(jsonObject.optString("modified_date"));
                }
            } catch (Exception e) {
                mSignUpModel.setStatus(false);
            }
        }
        return mSignUpModel;
    }
}
