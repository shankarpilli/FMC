package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.customviews.RoundedCornersTransformation;
import com.versatilemobitech.fmc.fragments.DetailViewFragment;
import com.versatilemobitech.fmc.fragments.WebViewFragment;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/6/2016.
 */
public class HomeAdapter extends BaseAdapter {

    private Context mContext;
    private HomeActivity dashboardActivity;
    private LayoutInflater mLayoutInflater;
    private ArrayList<HomeDataModel> homeDataModels;
    private Fragment fragment;


    public HomeAdapter(HomeActivity dashboardActivity, Context context, Fragment fragment, ArrayList<HomeDataModel> homeDataModels) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        HomeItemHolder mHomeItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.home_data_item,
                    null);
            mHomeItemHolder = new HomeItemHolder();
            mHomeItemHolder.post_image = (ImageView) convertView.findViewById(R.id.post_image);
            mHomeItemHolder.image_doc = (ImageView) convertView.findViewById(R.id.image_doc);
            mHomeItemHolder.image_data = (ImageView) convertView.findViewById(R.id.image_data);
            mHomeItemHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            mHomeItemHolder.txt_company = (TextView) convertView.findViewById(R.id.txt_company);
            mHomeItemHolder.txt_time_date = (TextView) convertView.findViewById(R.id.txt_time_date);

            mHomeItemHolder.txt_total_comments = (TextView) convertView.findViewById(R.id.txt_total_comments);
            mHomeItemHolder.txt_comment = (TextView) convertView.findViewById(R.id.txt_comment);
            mHomeItemHolder.txt_share = (TextView) convertView.findViewById(R.id.txt_share);
            mHomeItemHolder.txt_sub_name = (TextView) convertView.findViewById(R.id.txt_sub_name);
            mHomeItemHolder.txt_post_message = (TextView) convertView.findViewById(R.id.txt_post_message);

            mHomeItemHolder.txt_share_icon = (TextView) convertView.findViewById(R.id.txt_share_icon);
            mHomeItemHolder.txt_comment_icon = (TextView) convertView.findViewById(R.id.txt_comment_icon);

            mHomeItemHolder.txt_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_company.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_time_date.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_post_message.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_sub_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_share.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

            mHomeItemHolder.txt_total_comments.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_comment.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            mHomeItemHolder.txt_share.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

            mHomeItemHolder.txt_share_icon.setTypeface(Utility.setTypeFace_matirealicons(mContext));
            mHomeItemHolder.txt_comment_icon.setTypeface(Utility.setTypeFace_matirealicons(mContext));

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
                    .load(mHomeDataModel.getProfile_pic()).transform(new RoundedCornersTransformation(10, 1))
                    .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                    .into(mHomeItemHolder.post_image);
        if (!Utility.isValueNullOrEmpty(mHomeDataModel.getPost_image()) && (mHomeDataModel.getPost_image().contains(".jpg") || mHomeDataModel.getPost_image().contains(".png"))) {
            mHomeItemHolder.image_data.setVisibility(View.VISIBLE);
            mHomeItemHolder.image_doc.setVisibility(View.GONE);
            Picasso.with(mContext)
                    .load(mHomeDataModel.getPost_image())
                    .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                    .into(mHomeItemHolder.image_data);
        } else if (!Utility.isValueNullOrEmpty(mHomeDataModel.getPost_doc()) && mHomeDataModel.getDoc_extension().equalsIgnoreCase("pdf")) {
            mHomeItemHolder.image_data.setVisibility(View.GONE);
            mHomeItemHolder.image_doc.setVisibility(View.VISIBLE);
            mHomeItemHolder.image_doc.setImageDrawable(Utility.getDrawable(mContext, R.drawable.pdf_image));
        } else if (!Utility.isValueNullOrEmpty(mHomeDataModel.getPost_doc()) && (mHomeDataModel.getDoc_extension().equalsIgnoreCase("doc")
                || mHomeDataModel.getDoc_extension().equalsIgnoreCase("docx"))) {
            mHomeItemHolder.image_data.setVisibility(View.GONE);
            mHomeItemHolder.image_doc.setVisibility(View.VISIBLE);
            mHomeItemHolder.image_doc.setImageDrawable(Utility.getDrawable(mContext, R.drawable.doc_image));
        } else {
            mHomeItemHolder.image_data.setVisibility(View.GONE);
            mHomeItemHolder.image_doc.setVisibility(View.GONE);
        }

        mHomeItemHolder.txt_sub_name.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getCompany_name()));
        mHomeItemHolder.txt_time_date.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getDatetime()));
        if (mHomeDataModel.getComments_count() == 1) {
            mHomeItemHolder.txt_total_comments.setText("" + mHomeDataModel.getComments_count() + " comment");
        } else {
            mHomeItemHolder.txt_total_comments.setText("" + mHomeDataModel.getComments_count() + " comments");
        }

        mHomeItemHolder.image_doc.setTag(position);
        mHomeItemHolder.image_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tagPosition = (int) view.getTag();
                Bundle bundle = new Bundle();
                bundle.putString("file_url", homeDataModels.get(tagPosition).getPost_doc());
                Utility.navigateDashBoardFragment(new WebViewFragment(), WebViewFragment.TAG, bundle, dashboardActivity);
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("post_id", homeDataModels.get(position).getPost_id());
                Utility.navigateDashBoardFragment(new DetailViewFragment(), DetailViewFragment.TAG, bundle, dashboardActivity);
            }
        });

        mHomeItemHolder.txt_share.setTag(position);
        mHomeItemHolder.txt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tagPosition = (int) view.getTag();
                if (!Utility.isValueNullOrEmpty(homeDataModels.get(tagPosition).getPost_image()) &&
                        (homeDataModels.get(tagPosition).getPost_image().contains(".jpg") || homeDataModels.get(tagPosition).getPost_image().contains(".png"))) {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text() + " " + homeDataModels.get(tagPosition).getPost_image());
                } else if (!Utility.isValueNullOrEmpty(homeDataModels.get(tagPosition).getPost_doc()) && homeDataModels.get(tagPosition).getDoc_extension().equalsIgnoreCase("pdf")) {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text() + " " + homeDataModels.get(tagPosition).getPost_doc());
                } else if (!Utility.isValueNullOrEmpty(homeDataModels.get(tagPosition).getPost_doc()) && (homeDataModels.get(tagPosition).getDoc_extension().equalsIgnoreCase("doc")
                        || homeDataModels.get(tagPosition).getDoc_extension().equalsIgnoreCase("docx"))) {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text() + " " + homeDataModels.get(tagPosition).getPost_doc());
                } else {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text());
                }
            }
        });

        return convertView;
    }

    private void showShareDialog(String data) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "FM Council");
        shareIntent
                .putExtra(
                        Intent.EXTRA_TEXT,
                        data);
        mContext.startActivity(Intent.createChooser(shareIntent, "Share Using"));
    }


    private class HomeItemHolder {
        private ImageView post_image;
        private ImageView image_doc;
        private TextView txt_name;
        private TextView txt_company;
        private TextView txt_time_date;
        private TextView txt_post_message;
        private TextView txt_total_comments;
        private TextView txt_comment;
        private TextView txt_share;
        private TextView txt_sub_name;
        private TextView txt_share_icon;
        private TextView txt_comment_icon;
        private ImageView image_data;
    }
}
