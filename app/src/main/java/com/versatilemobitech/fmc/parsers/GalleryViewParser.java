package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.GalleryViewModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class GalleryViewParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        GalleryViewModel mGalleryViewModel = new GalleryViewModel();
        if (response != null) {
            mGalleryViewModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mGalleryViewModel.setMessage(jsonObject.optString("success"));

                mGalleryViewModel.setTotal_number_of_posts(jsonObject.optString("total_number_of_posts"));
                if(jsonObject.has("gallery_details")){
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

                }
            } catch (Exception e) {
                mGalleryViewModel.setStatus(false);
            }
        }
        return mGalleryViewModel;
    }
}
