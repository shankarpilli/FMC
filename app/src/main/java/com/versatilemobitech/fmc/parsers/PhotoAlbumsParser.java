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
public class PhotoAlbumsParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        GalleryFolderModel mGalleryFolderModel = new GalleryFolderModel();
        if (response != null) {
            mGalleryFolderModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mGalleryFolderModel.setMessage(jsonObject.optString("success"));

                mGalleryFolderModel.setTotal_number_of_posts(jsonObject.optString("total_number_of_posts"));
                if(jsonObject.has("album_details")){
                    JSONArray mArray = jsonObject.getJSONArray("album_details");
                    ArrayList<GalleryFolderModel> mList = new ArrayList<>();
                    for(int i = 0;i<mArray.length();i++) {
                        GalleryFolderModel mmGalleryFolderModel = new GalleryFolderModel();
                        JSONObject mObj = (JSONObject) mArray.get(i);
                        mmGalleryFolderModel.setAlbum_name(mObj.optString("album_name"));
                        mmGalleryFolderModel.setAlbum_image_path(mObj.optString("album_image_path"));
                        mmGalleryFolderModel.setPhoto_album_id(mObj.optString("photo_album_id"));

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
