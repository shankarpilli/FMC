package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.GalleryViewModel;
import com.versatilemobitech.fmc.models.GetPostsModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class GetPostsParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        GetPostsModel mGetPostsModel = new GetPostsModel();
        if (response != null) {
            mGetPostsModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mGetPostsModel.setMessage(jsonObject.optString("success"));

                mGetPostsModel.setTotal_number_of_posts(jsonObject.optString("total_number_of_posts"));
                /*if(jsonObject.has("gallery_details")){
                    JSONArray mArray = jsonObject.getJSONArray("gallery_details");
                    ArrayList<GalleryViewModel> mList = new ArrayList<>();
                    for(int i = 0;i<mArray.length();i++) {
                        JSONObject mObj = (JSONObject) mArray.get(i);
                        GalleryViewModel mmGalleryViewModel = new GalleryViewModel();

                        mmGalleryViewModel.setImage_path(mObj.optString("image_path"));
                        mmGalleryViewModel.setPhoto_id(mObj.optString("photo_id"));
                        mList.add(mmGalleryViewModel);
                    }
                    mGalleryViewModel.setmList(mList);

                }*/
            } catch (Exception e) {
                mGetPostsModel.setStatus(false);
            }
        }
        return mGetPostsModel;
    }
}
