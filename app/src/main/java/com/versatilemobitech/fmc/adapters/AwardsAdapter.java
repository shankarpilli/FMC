package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * Created by Manikanta on 11/7/2016.
 */

public class AwardsAdapter extends BaseAdapter {

private Context mContext;
    private LayoutInflater mLayoutInflater;

    public AwardsAdapter(Context mParent) {
        mContext = mParent;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GalleryHolder mGalleryHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_gallery,
                    null);
            mGalleryHolder = new GalleryHolder();
            mGalleryHolder.tv_award_year = (TextView)convertView.findViewById(R.id.tv_award_year);
            mGalleryHolder.tv_award_year.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            convertView.setTag(mGalleryHolder);
/*            grid = inflater.inflate(R.layout.item_gallery, null);*/

        } else {
            mGalleryHolder = (GalleryHolder) convertView.getTag();
        }


        return convertView;
    }

   private class GalleryHolder{

        private TextView tv_award_year;

    }
}
