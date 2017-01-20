package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.customviews.TouchImageView;
import com.versatilemobitech.fmc.models.GalleryViewModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.List;

/**
 * Created by shankar on 1/20/17.
 */

public class ImageViewPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<GalleryViewModel> mImageGallerySerializableList;


    public ImageViewPagerAdapter(Context parent, List<GalleryViewModel> stringList) {
        this.mImageGallerySerializableList = stringList;
        mLayoutInflater = (LayoutInflater) parent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(100, 170);
            mLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public int getCount() {
        return mImageGallerySerializableList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout;
        imageLayout = mLayoutInflater.inflate(R.layout.view_pager_item,
                view, false);
        assert imageLayout != null;
        TouchImageView imageView = (TouchImageView) imageLayout.findViewById(R.id.imageView);
        Utility.UILpicLoading(imageView, mImageGallerySerializableList.get(position).getImage_path(), null, R.drawable.folder_icon);
        view.addView(imageLayout, 0);
        return imageLayout;
    }

}
