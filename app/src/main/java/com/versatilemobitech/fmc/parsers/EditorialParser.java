package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.EditorialsModel;
import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class EditorialParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        EditorialsModel mEditorialsModel = new EditorialsModel();
        if (response != null) {
            mEditorialsModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mEditorialsModel.setMessage(jsonObject.optString("msg"));

                mEditorialsModel.setTotal_number_of_posts(jsonObject.optString("total_number_of_posts"));
                if(jsonObject.has("books_details")){
                    JSONArray mArray = jsonObject.getJSONArray("books_details");
                    ArrayList<EditorialsModel> mEditorialsModelList = new ArrayList<>();
                    for(int i = 0;i<mArray.length();i++){
                        JSONObject mJObj = (JSONObject) mArray.get(i);
                        EditorialsModel mEditorialsModelItem = new EditorialsModel();

                        mEditorialsModelItem.setBook_name(mJObj.optString("book_name"));
                        mEditorialsModelItem.setBook_path(mJObj.optString("book_path"));
                        mEditorialsModelItem.setBook_description(mJObj.optString("book_description"));
                        mEditorialsModelItem.setBig_Image(mJObj.optString("book_image"));
                        mEditorialsModelItem.setSource(mJObj.optString("source"));
                        mEditorialsModelItem.setSmall_Image(mJObj.optString("Small_Image"));
                        mEditorialsModelItem.setBig_Image("Big_Image");
                        mEditorialsModelItem.setBook_id(mJObj.optString("book_id"));

                        mEditorialsModelList.add(mEditorialsModelItem);
                    }
                    mEditorialsModel.setEditorialsModels(mEditorialsModelList);
                }

            } catch (Exception e) {
                mEditorialsModel.setStatus(false);
            }
        }
        return mEditorialsModel;
    }
}
