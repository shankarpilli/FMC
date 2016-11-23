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
                        mEventsModel = new EventsModel();

                        mEventsModel.setmEventTitle(mJObj.optString("event_title"));
                        mEventsModel.setmVenue(mJObj.optString("venue"));
                        mEventsModel.setmOrganizedBy(mJObj.optString("organized_by"));
                        mEventsModel.setmContactPersonName(mJObj.optString("contat_person_name"));
                        mEventsModel.setmContactInfo(mJObj.optString("conact_info"));
                        mEventsModel.setmEventDateTime(mJObj.optString("event_date_time"));
                        mEventsModel.setmPostedDate(mJObj.optString("posted_date"));
                        mEventsModel.setmFullDetails(mJObj.optString("full_details"));
                        mEventsModel.setmResponse(mJObj.optInt("response"));

                        mEventsModelList.add(mEventsModel);
                    }
                }

            } catch (Exception e) {
                mEventsModel.setStatus(false);
            }
        }
        return mEventsModel;
    }
}
