package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 06-11-2016.
 */

public class AwardsDetailListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<EventsModel> mEventsModelList;
    private EventsModel mEventsModel;

    public AwardsDetailListAdapter(Context mParent) {
        mContext = mParent;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public EventsModel getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AwardsListHolder mAwardsListHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_awards_list,
                    null);
            mAwardsListHolder = new AwardsListHolder();
            convertView.setTag(mAwardsListHolder);
        } else {
            mAwardsListHolder = (AwardsListHolder) convertView.getTag();
        }

        mAwardsListHolder.iv_view_details = (ImageView)convertView.findViewById(R.id.iv_view_details);
        mAwardsListHolder.ll_detail_view = (LinearLayout) convertView.findViewById(R.id.ll_detail_view);

        mAwardsListHolder.tv_company_name = (TextView) convertView.findViewById(R.id.tv_company_name);
        mAwardsListHolder.tv_company_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
        mAwardsListHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        mAwardsListHolder.tv_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        return convertView;
    }



    private class AwardsListHolder {

       private ImageView iv_view_details;
       private LinearLayout ll_detail_view;

        private TextView tv_company_name;
        private TextView tv_name;
    }
}
