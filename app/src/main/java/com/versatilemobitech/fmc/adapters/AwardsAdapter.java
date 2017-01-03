package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.AwardDetailsModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Manikanta on 11/7/2016.
 */

public class AwardsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<AwardDetailsModel> awardDetailsModels;


    public AwardsAdapter(Context context, ArrayList<AwardDetailsModel> awardDetailsModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.awardDetailsModels = awardDetailsModels;
    }

    @Override
    public int getCount() {
        return awardDetailsModels.size();
    }

    @Override
    public AwardDetailsModel getItem(int position) {
        return awardDetailsModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        AwardsAdapter.AwardsHolder mAwardsHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_gallery,
                    null);
            mAwardsHolder = new AwardsAdapter.AwardsHolder();
            mAwardsHolder.tv_award_year = (TextView) convertView.findViewById(R.id.tv_award_year);
            mAwardsHolder.iv_item_award = (ImageView) convertView.findViewById(R.id.iv_item_award);
            mAwardsHolder.tv_award_year.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            convertView.setTag(mAwardsHolder);
        } else {
            mAwardsHolder = (AwardsAdapter.AwardsHolder) convertView.getTag();
        }

        AwardDetailsModel mAwardDetailsModel = (AwardDetailsModel) getItem(position);

        mAwardsHolder.tv_award_year.setText(""+mAwardDetailsModel.getYear());

        /*Picasso.with(mContext)
                .load(mAwardDetailsModel.getProfile_pic())
                .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                .into(mGalleryFolderHolder.iv_gallery_item);*/
        return convertView;
    }

    private class AwardsHolder {
        private TextView tv_award_year;
        private ImageView iv_item_award;
    }
}
