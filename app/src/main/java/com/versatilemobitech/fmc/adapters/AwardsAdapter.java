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

    public AwardsAdapter(Context mParent) {
        mContext = mParent;
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
        View grid;
        GalleryHolder mGalleryHolder = null;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_gallery, null);
            mGalleryHolder = new GalleryHolder();

        } else {
            grid = (View) convertView;
        }

        mGalleryHolder.tv_award_year = (TextView)grid.findViewById(R.id.tv_award_year);
        mGalleryHolder.tv_award_year.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        return grid;
    }

   private class GalleryHolder{

        private TextView tv_award_year;

    }
}
