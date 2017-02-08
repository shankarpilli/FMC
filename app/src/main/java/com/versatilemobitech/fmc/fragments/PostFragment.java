package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class PostFragment extends DialogFragment {

    public static final String TAG = "PostFragment";
    private HomeActivity mParent;
    private View rootView;

    private LinearLayout ll_history;

    static PostFragment newInstance() {
        PostFragment f = new PostFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent,
                Utility.getResourcesString(getActivity(), R.string.home)));
        rootView = inflater.inflate(R.layout.fragment_share, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

    }
}
