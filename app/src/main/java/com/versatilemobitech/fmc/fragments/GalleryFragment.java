package com.versatilemobitech.fmc.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.utility.Utility;


public class GalleryFragment extends Fragment {
    public static final String TAG = "GalleryFragment";
    private DashboardActivity mParent;
    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.events));
        rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

    }
}