package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.AwardDetailsModel;
import com.versatilemobitech.fmc.models.AwardsYearModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class AwardsYearParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        AwardsYearModel mAwardsYearModel = new AwardsYearModel();
        if (response != null) {
            mAwardsYearModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);

                if (jsonObject.has("award_years")) {
                    JSONArray mArray = jsonObject.getJSONArray("award_years");
                    ArrayList<AwardsYearModel> mAwardsYearModelList = new ArrayList<>();
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject mJObj = (JSONObject) mArray.get(i);
                        AwardsYearModel mAwardsYearModelItem = new AwardsYearModel();
                        mAwardsYearModelItem.setYear(mJObj.optString("year"));
                        mAwardsYearModelList.add(mAwardsYearModelItem);
                    }
                    mAwardsYearModel.setAwardsYearModels(mAwardsYearModelList);
                }

            } catch (Exception e) {
                mAwardsYearModel.setStatus(false);
            }
        }
        return mAwardsYearModel;
    }
}
