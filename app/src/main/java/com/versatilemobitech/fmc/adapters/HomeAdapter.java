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
import com.versatilemobitech.fmc.models.HomeDataModel;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/6/2016.
 */
public class HomeAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<HomeDataModel> homeDataModels;


    public HomeAdapter(Context context, ArrayList<HomeDataModel> homeDataModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.homeDataModels = homeDataModels;
    }


    @Override
    public int getCount() {
        // return homeDataModels.size();
        return 2;
    }

    @Override
    public HomeDataModel getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HomeItemHolder mHomeItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.home_data_item,
                    null);
            mHomeItemHolder = new HomeItemHolder();
            mHomeItemHolder.ll_comments = (LinearLayout) convertView.findViewById(R.id.ll_comments);
            convertView.setTag(mHomeItemHolder);
        } else {
            mHomeItemHolder = (HomeItemHolder) convertView.getTag();
        }

        HomeDataModel leftMenuModel = (HomeDataModel) getItem(position);

        LinearLayout layout_list_header = (LinearLayout) mLayoutInflater.inflate(R.layout.
                home_comment_item, null);
        mHomeItemHolder.ll_comments.addView(layout_list_header);

        return convertView;
    }

    private class HomeItemHolder {
        private TextView txt_left_drawer_text;
        private ImageView img_left_drawer_icon;
        private LinearLayout ll_comments;
    }
}
