package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.GalleryViewModel;
import com.versatilemobitech.fmc.models.MembersModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class MembersParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        MembersModel mMembersModel = new MembersModel();
        if (response != null) {
            mMembersModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mMembersModel.setMessage(jsonObject.optString("success"));

                if(jsonObject.has("Member_details")){
                    JSONArray mArray = jsonObject.getJSONArray("Member_details");
                    ArrayList<MembersModel> mList = new ArrayList<>();
                    for(int i = 0;i<mArray.length();i++) {
                        JSONObject mObj = (JSONObject) mArray.get(i);
                        MembersModel mMembersModelItem = new MembersModel();
                        mMembersModelItem.setUser_id(mObj.optString("user_id"));
                        mMembersModelItem.setmName(mObj.optString("first_name"));
                        mMembersModelItem.setmCompany(mObj.optString("Company_name"));
                        mList.add(mMembersModelItem);
                    }
                    mMembersModel.setMembersModels(mList);

                }
            } catch (Exception e) {
                mMembersModel.setStatus(false);
            }
        }
        return mMembersModel;
    }
}
