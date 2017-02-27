package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.CSRViewModel;
import com.versatilemobitech.fmc.models.GalleryViewModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/25/2016.
 */
public class CSRViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<CSRViewModel> galleryViewModels;


    public CSRViewAdapter(Context context, ArrayList<CSRViewModel> galleryViewModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.galleryViewModels = galleryViewModels;
    }

    @Override
    public int getCount() {
        //return galleryViewModels.size();
        return galleryViewModels.size();
    }

    @Override
    public CSRViewModel getItem(int position) {
        return galleryViewModels.get(position);
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
            mGalleryViewHolder.iv_item_gallery = (ImageView) convertView.findViewById(R.id.iv_item_gallery);
            convertView.setTag(mGalleryViewHolder);
        } else {
            mGalleryViewHolder = (GalleryViewHolder) convertView.getTag();
        }

        CSRViewModel mGalleryViewModel = (CSRViewModel) getItem(position);

        Picasso.with(mContext)
                .load(mGalleryViewModel.getImage_path())
                .skipMemoryCache()
                .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                .into(mGalleryViewHolder.iv_item_gallery);

        return convertView;
    }

    private class GalleryViewHolder {
        private ImageView iv_item_gallery;
    }
}
