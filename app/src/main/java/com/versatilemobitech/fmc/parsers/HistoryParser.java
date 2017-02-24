package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.HistoryModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONObject;

/**
 * Created by Shankar on 11/20/2016.
 */
public class HistoryParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        HistoryModel mHistoryModel = new HistoryModel();
        if (response != null) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.has("history_text")) {
                    mHistoryModel.setStatus(true);
                    mHistoryModel.setHistory_text(jsonObject.optString("history_text"));
                } else {
                    mHistoryModel.setStatus(false);
                }
            } catch (Exception e) {
                mHistoryModel.setStatus(false);
            }
        }
        return mHistoryModel;
    }
}
