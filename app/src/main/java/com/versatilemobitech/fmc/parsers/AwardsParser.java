package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.AwardDetailsModel;
import com.versatilemobitech.fmc.models.AwardListModel;
import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class AwardsParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        AwardListModel mAwardListModel = new AwardListModel();
        if (response != null) {
            mAwardListModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);

                mAwardListModel.setTotal_number_of_posts(jsonObject.optString("total_number_of_posts"));
                if (jsonObject.has("award_details")) {
                    JSONArray mArray = jsonObject.getJSONArray("award_details");
                    ArrayList<AwardDetailsModel> mAwardDetailsModelList = new ArrayList<>();
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject mJObj = (JSONObject) mArray.get(i);
                        AwardDetailsModel mAwardDetailsModel = new AwardDetailsModel();
                        mAwardDetailsModel.setYear(mJObj.optString("year"));
                        mAwardDetailsModel.setAward_name(mJObj.optString("award_name"));
                        mAwardDetailsModel.setAward_description(mJObj.optString("award_description"));
                        mAwardDetailsModel.setAward_doc(mJObj.optString("award_doc"));
                        mAwardDetailsModel.setAward_id(mJObj.optString("award_id"));
                        mAwardDetailsModel.setFirst_name(mJObj.optString("first_name"));
                        mAwardDetailsModel.setLast_name("last_name");
                        mAwardDetailsModel.setCompany_name(mJObj.optString("company_name"));
                        mAwardDetailsModel.setProfile_pic(mJObj.optString("profile_pic"));
                        mAwardDetailsModelList.add(mAwardDetailsModel);
                    }
                    mAwardListModel.setmAwardListModel(mAwardDetailsModelList);
                }

            } catch (Exception e) {
                mAwardListModel.setStatus(false);
            }
        }
        return mAwardListModel;
    }
}
