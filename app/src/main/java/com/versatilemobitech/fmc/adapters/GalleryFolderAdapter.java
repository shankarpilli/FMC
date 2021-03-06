package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/25/2016.
 */
public class GalleryFolderAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<GalleryFolderModel> galleryFolderModels;


    public GalleryFolderAdapter(Context context, ArrayList<GalleryFolderModel> galleryFolderModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.galleryFolderModels = galleryFolderModels;
    }

    @Override
    public int getCount() {
        return galleryFolderModels.size();
    }

    @Override
    public GalleryFolderModel getItem(int position) {
        return galleryFolderModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        GalleryFolderHolder mGalleryFolderHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.gallery_folder_item,
                    null);
            mGalleryFolderHolder = new GalleryFolderHolder();
            mGalleryFolderHolder.txt_folder_name = (TextView) convertView.findViewById(R.id.txt_folder_name);
            mGalleryFolderHolder.iv_gallery_item = (ImageView) convertView.findViewById(R.id.iv_gallery_item);
            mGalleryFolderHolder.txt_folder_name.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
            convertView.setTag(mGalleryFolderHolder);
        } else {
            mGalleryFolderHolder = (GalleryFolderHolder) convertView.getTag();
        }

        GalleryFolderModel leftMenuModel = (GalleryFolderModel) getItem(position);

        mGalleryFolderHolder.txt_folder_name.setText(Utility.capitalizeFirstLetter(leftMenuModel.getAlbum_name()));

        Picasso.with(mContext)
                .load(leftMenuModel.getAlbum_image_path())
                .skipMemoryCache()
                .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                .into(mGalleryFolderHolder.iv_gallery_item);
        return convertView;
    }

    private class GalleryFolderHolder {
        private TextView txt_folder_name;
        private ImageView iv_gallery_item;
    }
}
