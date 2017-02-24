package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class CSRParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        GalleryFolderModel mGalleryFolderModel = new GalleryFolderModel();
        if (response != null) {
            mGalleryFolderModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mGalleryFolderModel.setMessage(jsonObject.optString("success"));

                mGalleryFolderModel.setTotal_number_of_posts(jsonObject.optString("total_number_of_posts"));
                if(jsonObject.has("csr_category")){
                    JSONArray mArray = jsonObject.getJSONArray("csr_category");
                    ArrayList<GalleryFolderModel> mList = new ArrayList<>();
                    for(int i = 0;i<mArray.length();i++) {
                        GalleryFolderModel mmGalleryFolderModel = new GalleryFolderModel();
                        JSONObject mObj = (JSONObject) mArray.get(i);
                        mmGalleryFolderModel.setAlbum_name(mObj.optString("category_name"));
                        mmGalleryFolderModel.setAlbum_image_path(mObj.optString("category_image_path"));
                        mmGalleryFolderModel.setPhoto_album_id(mObj.optString("csr_category_id"));

                        mList.add(mmGalleryFolderModel);
                    }
                    mGalleryFolderModel.setmList(mList);

                }
            } catch (Exception e) {
                mGalleryFolderModel.setStatus(false);
            }
        }
        return mGalleryFolderModel;
    }
}
