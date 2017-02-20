package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.models.KnowledgePartnerItemModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 12/22/2016.
 */
public class KnowledgePartnersAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<KnowledgePartnerItemModel> knowledgePartnerItemModels;
    private Typeface typeface;


    public KnowledgePartnersAdapter(Context context, ArrayList<KnowledgePartnerItemModel> knowledgePartnerItemModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.knowledgePartnerItemModels = knowledgePartnerItemModels;
        typeface = Utility.setTypeFaceRobotoRegular(mContext);
    }


    @Override
    public int getCount() {
        return knowledgePartnerItemModels.size();
    }

    @Override
    public KnowledgePartnerItemModel getItem(int position) {
        return knowledgePartnerItemModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        KnowledgePartnersItemHolder mKnowledgePartnersItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.knowledge_patners_item,
                    null);
            mKnowledgePartnersItemHolder = new KnowledgePartnersItemHolder();
            mKnowledgePartnersItemHolder.ll_items = (LinearLayout) convertView.findViewById(R.id.ll_items);
            mKnowledgePartnersItemHolder.txt_category = (TextView) convertView.findViewById(R.id.txt_category);
            mKnowledgePartnersItemHolder.txt_category.setTypeface(typeface);
            convertView.setTag(mKnowledgePartnersItemHolder);
        } else {
            mKnowledgePartnersItemHolder = (KnowledgePartnersItemHolder) convertView.getTag();
        }

        KnowledgePartnerItemModel knowledgePartnerItemModel = (KnowledgePartnerItemModel) getItem(position);
        mKnowledgePartnersItemHolder.txt_category.setText(knowledgePartnerItemModel.getCategory_name());


        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 8f);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 4f);
        lp2.setMargins(15, 15, 15, 15);

        mKnowledgePartnersItemHolder.ll_items.removeAllViews();

        int rowCount = 0;
        LinearLayout linearLayout = null;

        for (int j = 0; j < knowledgePartnerItemModel.getPartnersModels().size(); j++) {

            if (rowCount == 0) {
                linearLayout = new LinearLayout(mContext);
                linearLayout.setLayoutParams(lp1);
            }

            LinearLayout ll = (LinearLayout) mLayoutInflater.inflate(R.layout.knowledge_item, null);
            TextView txt_partner = (TextView) ll.findViewById(R.id.txt_partner);
            ImageView img_knowledge = (ImageView) ll.findViewById(R.id.img_knowledge);
            Picasso.with(mContext)
                    .load(knowledgePartnerItemModel.getPartnersModels().get(j).getPartner_logo()).transform(new CircleTransform())
                    .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                    .into(img_knowledge);
            txt_partner.setTypeface(typeface);
            txt_partner.setText("" + knowledgePartnerItemModel.getPartnersModels().get(j).getPartner_name());
            ll.setLayoutParams(lp2);
            linearLayout.addView(ll);

            rowCount += 1;
            if (rowCount == 2 || j == knowledgePartnerItemModel.getPartnersModels().size() - 1) {
                mKnowledgePartnersItemHolder.ll_items.addView(linearLayout);
                rowCount = 0;
            }
        }

        return convertView;
    }


    private class KnowledgePartnersItemHolder {
        LinearLayout ll_items;
        TextView txt_category;
    }
}
