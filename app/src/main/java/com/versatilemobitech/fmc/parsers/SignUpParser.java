package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.LoginModel;
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
            mSignUpModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mSignUpModel.setMessage(jsonObject.optString("success"));
                mSignUpModel.setName(jsonObject.optString("name"));
            } catch (Exception e) {
                mSignUpModel.setStatus(false);
            }
        }
        return null;
    }
}
