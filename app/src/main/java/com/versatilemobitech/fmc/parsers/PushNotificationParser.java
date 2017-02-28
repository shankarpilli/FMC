package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.CommentsModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.PushNotificationModel;

import org.json.JSONObject;

/**
 * Created by Shankar on 11/20/2016.
 */
public class PushNotificationParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        PushNotificationModel mPushNotificationModel = new PushNotificationModel();
        if (response != null) {
            mPushNotificationModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mPushNotificationModel.setMessage(jsonObject.optString("message"));
                /*mCommentsModel.setUser_id(jsonObject.optString("user_id"));
                mCommentsModel.setPost_id(jsonObject.optString("post_id"));
                mCommentsModel.setComment(jsonObject.optString("comment"));*/
            } catch (Exception e) {
                mPushNotificationModel.setStatus(false);
            }
        }
        return mPushNotificationModel;
    }
}
