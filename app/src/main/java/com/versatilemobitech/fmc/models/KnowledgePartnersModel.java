package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/6/2016.
 */
public class KnowledgePartnersModel extends Model {
    private String total_number_of_posts;
    private ArrayList<KnowledgePartnerItemModel> knowledgePartnerItemModels;

    public String getTotal_number_of_posts() {
        return total_number_of_posts;
    }

    public void setTotal_number_of_posts(String total_number_of_posts) {
        this.total_number_of_posts = total_number_of_posts;
    }

    public ArrayList<KnowledgePartnerItemModel> getKnowledgePartnerItemModels() {
        return knowledgePartnerItemModels;
    }

    public void setKnowledgePartnerItemModels(ArrayList<KnowledgePartnerItemModel> knowledgePartnerItemModels) {
        this.knowledgePartnerItemModels = knowledgePartnerItemModels;
    }
}
