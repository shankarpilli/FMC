package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.CommentsModel;
import com.versatilemobitech.fmc.models.LoginModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONObject;

/**
 * Created by Shankar on 11/20/2016.
 */
public class CommentsParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        CommentsModel mCommentsModel = new CommentsModel();
        if (response != null) {
            mCommentsModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mCommentsModel.setMessage(jsonObject.optString("message"));
                /*mCommentsModel.setUser_id(jsonObject.optString("user_id"));
                mCommentsModel.setPost_id(jsonObject.optString("post_id"));
                mCommentsModel.setComment(jsonObject.optString("comment"));*/
            } catch (Exception e) {
                mCommentsModel.setStatus(false);
            }
        }
        return mCommentsModel;
    }
}
