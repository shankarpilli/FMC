package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.GetPostsModel;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.models.KnowledgePartnerItemModel;
import com.versatilemobitech.fmc.models.KnowledgePartnersModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.PartnersModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class GetKnowledgePartnersParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        KnowledgePartnersModel mKnowledgePartnersModel = new KnowledgePartnersModel();
        if (response != null) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                mKnowledgePartnersModel.setStatus(true);
                if (jsonObject.has("success")) {
                    mKnowledgePartnersModel.setMessage(jsonObject.optString("success"));
                }

                if (jsonObject.has("knowledge_partner_details")) {
                    JSONArray mArray = jsonObject.getJSONArray("knowledge_partner_details");
                    ArrayList<KnowledgePartnerItemModel> knowledgePartnerItemModels = new ArrayList<>();
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject mObj = (JSONObject) mArray.get(i);
                        KnowledgePartnerItemModel mKnowledgePartnerItemModel = new KnowledgePartnerItemModel();
                        mKnowledgePartnerItemModel.setCategory_name(mObj.optString("category_name"));
                        JSONArray mPartnersArray = mObj.getJSONArray("partners");
                        ArrayList<PartnersModel> partnersModels = new ArrayList<>();
                        for (int j = 0; j < mPartnersArray.length(); j++) {
                            JSONObject mPartnerObj = (JSONObject) mPartnersArray.get(j);
                            PartnersModel partnersModel = new PartnersModel();
                            partnersModel.setPartner_name(mPartnerObj.optString("partner_name"));
                            partnersModel.setPartner_logo(mPartnerObj.optString("partner_logo"));
                            partnersModel.setKnowledge_partners_id(mPartnerObj.optString("knowledge_partners_id"));
                            partnersModels.add(partnersModel);
                        }
                        mKnowledgePartnerItemModel.setPartnersModels(partnersModels);
                        knowledgePartnerItemModels.add(mKnowledgePartnerItemModel);
                    }
                    mKnowledgePartnersModel.setKnowledgePartnerItemModels(knowledgePartnerItemModels);
                }
            } catch (Exception e) {
                mKnowledgePartnersModel.setStatus(false);
            }
        }
        return mKnowledgePartnersModel;
    }
}
