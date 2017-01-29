package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.ImageViewPagerAdapter;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * Created by shankar on 1/20/17.
 */

public class GalleryFullViewFragment extends Fragment {
    public static final String TAG = "GalleryFullViewFragment";
    private HomeActivity mParent;
    private View rootView;

    private String albumName;
    private ViewPager viewPager;
    private int mCurrentPosition = -1;
    private ImageViewPagerAdapter imageViewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();

        Bundle mBundle = getArguments();
        albumName = mBundle.getString("albumName");
        mCurrentPosition = mBundle.getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent, albumName));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_gallery_full_view, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        imageViewPagerAdapter = new ImageViewPagerAdapter(mParent, GalleryViewFragment.galleryViewModels);
        viewPager.setAdapter(imageViewPagerAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setCurrentItem(mCurrentPosition);
    }
}
