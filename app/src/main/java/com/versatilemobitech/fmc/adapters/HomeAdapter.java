package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.fragments.HomeFragment;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/6/2016.
 */
public class HomeAdapter extends BaseAdapter {

    private Context mContext;
    private DashboardActivity dashboardActivity;
    private LayoutInflater mLayoutInflater;
    private ArrayList<HomeDataModel> homeDataModels;
    private Fragment fragment;


    public HomeAdapter(DashboardActivity dashboardActivity, Context context, Fragment fragment, ArrayList<HomeDataModel> homeDataModels) {
        mContext = context;
        this.dashboardActivity = dashboardActivity;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.homeDataModels = homeDataModels;
        this.fragment = fragment;
    }


    @Override
    public int getCount() {
        return homeDataModels.size();
    }

    @Override
    public HomeDataModel getItem(int position) {
        return homeDataModels.get(position);
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
            mHomeItemHolder.post_image = (ImageView) convertView.findViewById(R.id.post_image);
            mHomeItemHolder.image_data = (ImageView) convertView.findViewById(R.id.image_data);
            mHomeItemHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            mHomeItemHolder.txt_company = (TextView) convertView.findViewById(R.id.txt_company);
            mHomeItemHolder.txt_time_date = (TextView) convertView.findViewById(R.id.txt_time_date);
            mHomeItemHolder.txt_send = (TextView) convertView.findViewById(R.id.txt_send);
            mHomeItemHolder.txt_post_message = (TextView) convertView.findViewById(R.id.txt_post_message);
            mHomeItemHolder.edt_comment = (EditText) convertView.findViewById(R.id.edt_comment);
            mHomeItemHolder.ll_comments = (LinearLayout) convertView.findViewById(R.id.ll_comments);

            mHomeItemHolder.txt_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_company.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_time_date.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_post_message.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.edt_comment.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_send.setTypeface(Utility.setTypeFace_fontawesome(mContext));

            convertView.setTag(mHomeItemHolder);
        } else {
            mHomeItemHolder = (HomeItemHolder) convertView.getTag();
        }

        HomeDataModel mHomeDataModel = (HomeDataModel) getItem(position);

        mHomeItemHolder.txt_name.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getFirst_name())
                + " " + Utility.capitalizeFirstLetter(mHomeDataModel.getLast_name()));
        mHomeItemHolder.txt_company.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getCompany_name()));
        mHomeItemHolder.txt_post_message.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getPost_text()));
        if (!Utility.isValueNullOrEmpty(mHomeDataModel.getProfile_pic()))
            Picasso.with(mContext)
                    .load(mHomeDataModel.getProfile_pic()).transform(new CircleTransform())
                    .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                    .into(mHomeItemHolder.post_image);
        if (!Utility.isValueNullOrEmpty(mHomeDataModel.getPost_image()))
            Picasso.with(mContext)
                    .load(mHomeDataModel.getPost_image()).transform(new CircleTransform())
                    .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                    .into(mHomeItemHolder.image_data);
        else
            mHomeItemHolder.image_data.setVisibility(View.GONE);

        final HomeItemHolder finalMHomeItemHolder = mHomeItemHolder;
        mHomeItemHolder.txt_send.setTag("" + position);
        mHomeItemHolder.txt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utility.isValueNullOrEmpty(finalMHomeItemHolder.edt_comment.getText().toString())) {
                    Utility.setSnackBarEnglish(dashboardActivity, finalMHomeItemHolder.edt_comment, "Please Enter comment");
                } else {
                    int po = Integer.parseInt(view.getTag().toString());
                    ((HomeFragment) fragment).commentOnPost(po, finalMHomeItemHolder.edt_comment.getText().toString());
                }
            }
        });
        /*mHomeItemHolder.txt_time_date.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getCompany_name()));*/

        /*LinearLayout layout_list_header = (LinearLayout) mLayoutInflater.inflate(R.layout.
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

        mHomeItemHolder.ll_comments.addView(layout_list_header);*/

        return convertView;
    }

    private class HomeItemHolder {
        private ImageView post_image;
        private TextView txt_name;
        private TextView txt_company;
        private TextView txt_time_date;
        private TextView txt_post_message;
        private TextView txt_send;
        private ImageView image_data;
        /*private TextView txt_comment_name;
        private TextView txt_comment_company;
        private TextView txt_comment_time_date;
        private TextView txt_comment_post_message;
        private TextView txt_reply;*/
        private EditText edt_comment;
        private LinearLayout ll_comments;
    }
}
