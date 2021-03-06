package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.models.LoginModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class EventsParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        EventsModel mEventsModel = new EventsModel();
        if (response != null) {
            mEventsModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mEventsModel.setMessage(jsonObject.optString("msg"));

                mEventsModel.setmTotalNumberOfPosts(jsonObject.optString("total_number_of_posts"));
                if(jsonObject.has("event_details")){
                    JSONArray mArray = jsonObject.getJSONArray("event_details");
                    ArrayList<EventsModel> mEventsModelList = new ArrayList<>();
                    for(int i = 0;i<mArray.length();i++){
                        JSONObject mJObj = (JSONObject) mArray.get(i);
                        EventsModel mmEventsModel = new EventsModel();

                        mmEventsModel.setEvent_title(mJObj.optString("event_title"));
                        mmEventsModel.setVenue(mJObj.optString("venue"));
                        mmEventsModel.setOrganized_by(mJObj.optString("organized_by"));
                        mmEventsModel.setContact(mJObj.optString("contact"));
                        mmEventsModel.setChief_guest(mJObj.optString("chief_guest"));
                        mmEventsModel.setDate_of_event(mJObj.optString("date_of_event"));
                        mmEventsModel.setDetails(mJObj.optString("Details"));
                        mmEventsModel.setEvent_id(mJObj.optString("event_id"));
                        mmEventsModel.setDatetime(mJObj.optString("datetime"));
                        mmEventsModel.setResponse(mJObj.optInt("response"));

                        mEventsModelList.add(mmEventsModel);
                    }
                    mEventsModel.setmEventsModelList(mEventsModelList);
                }

            } catch (Exception e) {
                mEventsModel.setStatus(false);
            }
        }
        return mEventsModel;
    }
}
