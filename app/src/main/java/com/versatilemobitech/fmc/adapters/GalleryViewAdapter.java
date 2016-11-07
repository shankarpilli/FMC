package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.GalleryViewModel;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/25/2016.
 */
public class GalleryViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<GalleryViewModel> galleryViewModels;


    public GalleryViewAdapter(Context context, ArrayList<GalleryViewModel> galleryViewModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.galleryViewModels = galleryViewModels;
    }

    @Override
    public int getCount() {
        //return galleryViewModels.size();
        return 10;
    }

    @Override
    public GalleryFolderModel getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GalleryViewHolder mGalleryViewHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.gallery_view_item,
                    null);
            mGalleryViewHolder = new GalleryViewHolder();
            convertView.setTag(mGalleryViewHolder);
        } else {
            mGalleryViewHolder = (GalleryViewHolder) convertView.getTag();
        }

        GalleryFolderModel leftMenuModel = (GalleryFolderModel) getItem(position);

        return convertView;
    }

    private class GalleryViewHolder {
        private TextView txt_left_drawer_text;
        private ImageView img_left_drawer_icon;
    }
}
