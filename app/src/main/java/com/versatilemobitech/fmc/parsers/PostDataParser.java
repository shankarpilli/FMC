package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.LoginModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.PostDataModel;

import org.json.JSONObject;

/**
 * Created by Shankar on 12/09/2016.
 */
public class PostDataParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        PostDataModel mPostDataModel = new PostDataModel();
        if (response != null) {
            mPostDataModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mPostDataModel.setMessage(jsonObject.optString("message"));
                mPostDataModel.setUser_id(jsonObject.optString("user_id"));
                mPostDataModel.setPost_text(jsonObject.optString("post_text"));
                mPostDataModel.setPost_image(jsonObject.optString("post_image"));
            } catch (Exception e) {
                mPostDataModel.setStatus(false);
            }
        }
        return mPostDataModel;
    }
}
