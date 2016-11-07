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
import com.versatilemobitech.fmc.models.MembersModel;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/25/2016.
 */
public class MembersAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<MembersModel> membersModels;


    public MembersAdapter(Context context, ArrayList<MembersModel> membersModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.membersModels = membersModels;
    }

    @Override
    public int getCount() {
        //return membersModels.size();
        return 20;
    }

    @Override
    public MembersModel getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MembersItemHolder mMembersItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.member_search_item,
                    null);
            mMembersItemHolder = new MembersItemHolder();
            mMembersItemHolder.txt_left_drawer_text = (TextView) convertView.findViewById(R.id.txt_left_drawer_text);
            mMembersItemHolder.img_left_drawer_icon = (ImageView) convertView.findViewById(R.id.img_left_drawer_icon);
            convertView.setTag(mMembersItemHolder);
        } else {
            mMembersItemHolder = (MembersItemHolder) convertView.getTag();
        }

        MembersModel leftMenuModel = (MembersModel) getItem(position);

        return convertView;
    }

    private class MembersItemHolder {
        private TextView txt_left_drawer_text;
        private ImageView img_left_drawer_icon;
    }
}
