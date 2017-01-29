package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class WelcomeFragment extends Fragment {
    public static final String TAG = "WelcomeFragment";
    private HomeActivity mParent;
    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent,
                Utility.getResourcesString(getActivity(), R.string.welcome_message)));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

    }
}
