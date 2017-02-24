package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.CSRViewModel;
import com.versatilemobitech.fmc.models.GalleryViewModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class CSRViewParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        CSRViewModel mCSRViewModel = new CSRViewModel();
        if (response != null) {
            mCSRViewModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mCSRViewModel.setMessage(jsonObject.optString("success"));

                mCSRViewModel.setTotal_number_of_posts(jsonObject.optString("total_number_of_posts"));
                if (jsonObject.has("csr_details")) {
                    JSONArray mArray = jsonObject.getJSONArray("csr_details");
                    ArrayList<CSRViewModel> mList = new ArrayList<>();
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject mObj = (JSONObject) mArray.get(i);
                        CSRViewModel mmCSRViewModel = new CSRViewModel();

                        mmCSRViewModel.setImage_path(mObj.optString("image_path"));
                        mmCSRViewModel.setImage_title(mObj.optString("image_title"));
                        mmCSRViewModel.setImage_description(mObj.optString("image_description"));
                        mmCSRViewModel.setCsr_id(mObj.optString("csr_id"));
                        mmCSRViewModel.setDatetime(mObj.optString("datetime"));
                        mList.add(mmCSRViewModel);
                    }
                    mCSRViewModel.setmList(mList);

                }
            } catch (Exception e) {
                mCSRViewModel.setStatus(false);
            }
        }
        return mCSRViewModel;
    }
}
