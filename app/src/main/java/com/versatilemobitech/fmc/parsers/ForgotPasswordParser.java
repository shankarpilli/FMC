package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.ForgotPasswordModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONObject;

/**
 * Created by Shankar on 1/14/2017.
 */
public class ForgotPasswordParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        ForgotPasswordModel mForgotPasswordModel = new ForgotPasswordModel();
        if (response != null) {
            mForgotPasswordModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mForgotPasswordModel.setMessage(jsonObject.optString("msg"));
                mForgotPasswordModel.setStatus(true);
            } catch (Exception e) {
                mForgotPasswordModel.setStatus(false);
            }
        }
        return mForgotPasswordModel;
    }
}