package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.LeftMenuModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/25/2016.
 */
public class LeftMenuAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<LeftMenuModel> leftMenuModels;


    public LeftMenuAdapter(Context context, ArrayList<LeftMenuModel> leftMenuModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.leftMenuModels = leftMenuModels;
    }

    @Override
    public int getCount() {
        return leftMenuModels.size();
    }

    @Override
    public Object getItem(int position) {
        return leftMenuModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LeftMenuItemHolder mLeftMenuItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_home_left_drawer_item,
                    null);
            mLeftMenuItemHolder = new LeftMenuItemHolder();
            mLeftMenuItemHolder.txt_left_drawer_text = (TextView) convertView.findViewById(R.id.txt_left_drawer_text);
            mLeftMenuItemHolder.img_left_drawer_icon = (ImageView) convertView.findViewById(R.id.img_left_drawer_icon);
            mLeftMenuItemHolder.txt_left_drawer_text.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            convertView.setTag(mLeftMenuItemHolder);
        } else {
            mLeftMenuItemHolder = (LeftMenuItemHolder) convertView.getTag();
        }

        LeftMenuModel leftMenuModel = (LeftMenuModel) getItem(position);
        mLeftMenuItemHolder.txt_left_drawer_text.setText("" + leftMenuModel.getName());
        mLeftMenuItemHolder.img_left_drawer_icon.setImageResource(leftMenuModel.getIcon());

        return convertView;
    }

    private class LeftMenuItemHolder {
        private TextView txt_left_drawer_text;
        private ImageView img_left_drawer_icon;
    }
}
