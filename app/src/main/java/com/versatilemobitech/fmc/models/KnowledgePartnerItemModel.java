package com.versatilemobitech.fmc.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 12/22/2016.
 */
public class KnowledgePartnerItemModel {
    private String category_name;
    private ArrayList<PartnersModel> partnersModels;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ArrayList<PartnersModel> getPartnersModels() {
        return partnersModels;
    }

    public void setPartnersModels(ArrayList<PartnersModel> partnersModels) {
        this.partnersModels = partnersModels;
    }
}
