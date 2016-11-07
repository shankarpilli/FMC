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
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_gallery, null);
            GalleryHolder mGalleryHolder = new GalleryHolder();
            /*convertView.setTag(mGalleryHolder);*/

        } else {
            grid = (View) convertView;
        }

        return grid;
    }

    private class GalleryHolder{

    }
}
