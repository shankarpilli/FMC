package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.EditorialsModel;
import com.versatilemobitech.fmc.models.MembersModel;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/25/2016.
 */
public class EditorialsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<EditorialsModel> editorialsModels;


    public EditorialsAdapter(Context context, ArrayList<EditorialsModel> editorialsModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.editorialsModels = editorialsModels;
    }

    @Override
    public int getCount() {
        //return membersModels.size();
        return 20;
    }

    @Override
    public EditorialsModel getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EditorialsItemHolder mEditorialsItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.editorials_item,
                    null);
            mEditorialsItemHolder = new EditorialsItemHolder();
            mEditorialsItemHolder.txt_left_drawer_text = (TextView) convertView.findViewById(R.id.txt_left_drawer_text);
            mEditorialsItemHolder.img_left_drawer_icon = (ImageView) convertView.findViewById(R.id.img_left_drawer_icon);
            convertView.setTag(mEditorialsItemHolder);
        } else {
            mEditorialsItemHolder = (EditorialsItemHolder) convertView.getTag();
        }

        EditorialsModel leftMenuModel = (EditorialsModel) getItem(position);

        return convertView;
    }

    private class EditorialsItemHolder {
        private TextView txt_left_drawer_text;
        private ImageView img_left_drawer_icon;
    }
}
