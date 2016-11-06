package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.models.LeftMenuModel;

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

        LeftMenuItemHolder mLeftMenuItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.home_data_item,
                    null);
            mLeftMenuItemHolder = new LeftMenuItemHolder();
            convertView.setTag(mLeftMenuItemHolder);
        } else {
            mLeftMenuItemHolder = (LeftMenuItemHolder) convertView.getTag();
        }

        HomeDataModel leftMenuModel = (HomeDataModel) getItem(position);

        return convertView;
    }

    private class LeftMenuItemHolder {
        private TextView txt_left_drawer_text;
        private ImageView img_left_drawer_icon;
    }
}
