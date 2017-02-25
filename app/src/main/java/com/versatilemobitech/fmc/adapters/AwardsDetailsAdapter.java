package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.AwardDetailsModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Manikanta on 11/7/2016.
 */

public class AwardsDetailsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<AwardDetailsModel> awardDetailsModels;
    private Typeface mTypefaceRobotoRegular;
    private Typeface mTypefaceRobotoBoldRegular;


    public AwardsDetailsAdapter(Context context, ArrayList<AwardDetailsModel> awardDetailsModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.awardDetailsModels = awardDetailsModels;
        mTypefaceRobotoRegular = Utility.setTypeFaceRobotoRegular(mContext);
        mTypefaceRobotoBoldRegular = Utility.setTypeFaceRobotoBold(mContext);
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

        AwardsDetailsAdapter.AwardsHolder mAwardsHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.awards_details_item,
                    null);
            mAwardsHolder = new AwardsDetailsAdapter.AwardsHolder();
            mAwardsHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            mAwardsHolder.txt_category_name = (TextView) convertView.findViewById(R.id.txt_category_name);
            mAwardsHolder.txt_company_name = (TextView) convertView.findViewById(R.id.txt_company_name);
            mAwardsHolder.txt_description = (TextView) convertView.findViewById(R.id.txt_description);
            mAwardsHolder.img_award = (ImageView) convertView.findViewById(R.id.img_award);
            mAwardsHolder.img_down = (ImageView) convertView.findViewById(R.id.img_down);
            mAwardsHolder.view_line = (View) convertView.findViewById(R.id.view_line);

            mAwardsHolder.txt_category_name.setTypeface(mTypefaceRobotoRegular);
            mAwardsHolder.txt_name.setTypeface(mTypefaceRobotoBoldRegular);
            mAwardsHolder.txt_company_name.setTypeface(mTypefaceRobotoBoldRegular);
            mAwardsHolder.txt_description.setTypeface(mTypefaceRobotoRegular);
            convertView.setTag(mAwardsHolder);
        } else {
            mAwardsHolder = (AwardsDetailsAdapter.AwardsHolder) convertView.getTag();
        }

        AwardDetailsModel mAwardDetailsModel = (AwardDetailsModel) getItem(position);

        String mName = "";
        if (!Utility.isValueNullOrEmpty(mAwardDetailsModel.getFirst_name())) {
            mName = Utility.capitalizeFirstLetter(mAwardDetailsModel.getFirst_name());
        }
        if (!Utility.isValueNullOrEmpty(mAwardDetailsModel.getLast_name())) {
            mName = mName + " " + Utility.capitalizeFirstLetter(mAwardDetailsModel.getLast_name());
        }

        mAwardsHolder.txt_name.setText(mName);

        if (!Utility.isValueNullOrEmpty(mAwardDetailsModel.getCompany_name())) {
            mAwardsHolder.txt_company_name.setText(Utility.capitalizeFirstLetter(mAwardDetailsModel.getCompany_name()));
            mAwardsHolder.txt_company_name.setVisibility(View.VISIBLE);
        } else {
            mAwardsHolder.txt_company_name.setText("");
            mAwardsHolder.txt_company_name.setVisibility(View.GONE);
        }

        if (!Utility.isValueNullOrEmpty(mAwardDetailsModel.getAward_name())) {
            mAwardsHolder.txt_category_name.setText(Utility.capitalizeFirstLetter(mAwardDetailsModel.getAward_name()));
            mAwardsHolder.txt_category_name.setVisibility(View.VISIBLE);
        } else {
            mAwardsHolder.txt_category_name.setText("");
            mAwardsHolder.txt_category_name.setVisibility(View.GONE);
        }

        Picasso.with(mContext)
                .load(mAwardDetailsModel.getProfile_pic())
                .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                .into(mAwardsHolder.img_award);

        if (mAwardDetailsModel.isVisible()) {
            mAwardsHolder.img_down.setImageDrawable(mContext.getResources().getDrawable(R.drawable.image_side));
            mAwardsHolder.view_line.setVisibility(View.VISIBLE);
            mAwardsHolder.txt_description.setVisibility(View.VISIBLE);
            mAwardsHolder.txt_description.setText(Utility.capitalizeFirstLetter(mAwardDetailsModel.getAward_description()));
        } else {
            mAwardsHolder.img_down.setImageDrawable(mContext.getResources().getDrawable(R.drawable.image_down));
            mAwardsHolder.view_line.setVisibility(View.GONE);
            mAwardsHolder.txt_description.setText(Utility.capitalizeFirstLetter(mAwardDetailsModel.getAward_description()));
            mAwardsHolder.txt_description.setVisibility(View.GONE);
        }
        mAwardsHolder.img_down.setId(position);
        mAwardsHolder.img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = view.getId();
                for (int i = 0; i < awardDetailsModels.size(); i++) {
                    AwardDetailsModel awardDetailsModel = awardDetailsModels.get(i);
                    if (i == position) {
                        awardDetailsModel.setVisible(true);
                    } else {
                        awardDetailsModel.setVisible(false);
                    }
                    awardDetailsModels.set(i, awardDetailsModel);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    private class AwardsHolder {
        private TextView txt_name;
        private TextView txt_category_name;
        private TextView txt_description;
        private TextView txt_company_name;
        private ImageView img_award;
        private ImageView img_down;
        private View view_line;
    }
}
