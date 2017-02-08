package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.PostLikeModel;

import org.json.JSONObject;

/**
 * Created by shankar on 2/8/2017.
 */

public class PostLikeParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        PostLikeModel mPostLikeModel = new PostLikeModel();
        if (response != null) {
            mPostLikeModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mPostLikeModel.setMessage(jsonObject.optString("message"));
            } catch (Exception e) {
                mPostLikeModel.setStatus(false);
            }
        }
        return mPostLikeModel;
    }
}