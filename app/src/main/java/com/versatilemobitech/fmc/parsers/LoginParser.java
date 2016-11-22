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
            mLoginModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mLoginModel.setMessage(jsonObject.optString("msg"));
                mLoginModel.setUser_id(jsonObject.optString("user_id"));
                mLoginModel.setUser_name(jsonObject.optString("user_name"));
                mLoginModel.setCompany_name(jsonObject.optString("company_name"));
                mLoginModel.setKey(jsonObject.optString("key"));
            } catch (Exception e) {
                mLoginModel.setStatus(false);
            }
        }
        return mLoginModel;
    }
}
