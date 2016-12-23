package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.CommentsModel;
import com.versatilemobitech.fmc.models.EventResponseModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONObject;

/**
 * Created by Shankar on 11/20/2016.
 */
public class EventsResponseParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        EventResponseModel mEventResponseModel = new EventResponseModel();
        if (response != null) {
            mEventResponseModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mEventResponseModel.setMessage(jsonObject.optString("message"));
                mEventResponseModel.setEvent_id(jsonObject.optString("event_id"));
                mEventResponseModel.setFollower_id(jsonObject.optString("follower_id"));
                mEventResponseModel.setResponse_text(jsonObject.optString("response_text"));
            } catch (Exception e) {
                mEventResponseModel.setStatus(false);
            }
        }
        return mEventResponseModel;
    }
}
