package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.utility.Utility;

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
            mHomeItemHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            mHomeItemHolder.txt_company = (TextView) convertView.findViewById(R.id.txt_company);
            mHomeItemHolder.txt_time_date = (TextView) convertView.findViewById(R.id.txt_time_date);
            mHomeItemHolder.txt_post_message = (TextView) convertView.findViewById(R.id.txt_post_message);
            mHomeItemHolder.edt_comment = (EditText) convertView.findViewById(R.id.edt_comment);
            mHomeItemHolder.ll_comments = (LinearLayout) convertView.findViewById(R.id.ll_comments);

            mHomeItemHolder.txt_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_company.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_time_date.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_post_message.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.edt_comment.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

            convertView.setTag(mHomeItemHolder);
        } else {
            mHomeItemHolder = (HomeItemHolder) convertView.getTag();
        }

        HomeDataModel leftMenuModel = (HomeDataModel) getItem(position);

        LinearLayout layout_list_header = (LinearLayout) mLayoutInflater.inflate(R.layout.
                home_comment_item, null);
        mHomeItemHolder.txt_comment_name = (TextView) layout_list_header.findViewById(R.id.txt_comment_name);
        mHomeItemHolder.txt_comment_company = (TextView) layout_list_header.findViewById(R.id.txt_comment_company);
        mHomeItemHolder.txt_comment_time_date = (TextView) layout_list_header.findViewById(R.id.txt_comment_time_date);
        mHomeItemHolder.txt_comment_post_message = (TextView) layout_list_header.findViewById(R.id.txt_comment_post_message);
        mHomeItemHolder.txt_reply = (TextView) layout_list_header.findViewById(R.id.txt_reply);

        mHomeItemHolder.txt_comment_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
        mHomeItemHolder.txt_comment_company.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
        mHomeItemHolder.txt_comment_time_date.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
        mHomeItemHolder.txt_comment_post_message.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
        mHomeItemHolder.txt_reply.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mHomeItemHolder.ll_comments.addView(layout_list_header);

        return convertView;
    }

    private class HomeItemHolder {
        private TextView txt_name;
        private TextView txt_company;
        private TextView txt_time_date;
        private TextView txt_post_message;
        private TextView txt_comment_name;
        private TextView txt_comment_company;
        private TextView txt_comment_time_date;
        private TextView txt_comment_post_message;
        private TextView txt_reply;
        private EditText edt_comment;
        private LinearLayout ll_comments;
    }
}
