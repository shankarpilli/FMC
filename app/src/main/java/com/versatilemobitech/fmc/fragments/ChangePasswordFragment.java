package com.versatilemobitech.fmc.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.designs.MaterialDialog;
import com.versatilemobitech.fmc.permissions.Permissions;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    public static final String TAG = "ChangePasswordFragment";
    private HomeActivity mParent;
    private String mToolBarTitle;
    private Toolbar mToolbar;
    private View rootView;


    private EditText etOldPasswordl;
    private EditText etNewPasswordl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.getResourcesString(getActivity(), R.string.contacts_us));
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        rootView = inflater.inflate(R.layout.change_password, container, false);
        //mParent.txt_our_tour.setText("" + mToolBarTitle);

        InitUI(rootView);

        return rootView;
    }


    private void InitUI(View rootView) {

        etOldPasswordl = (EditText)rootView.findViewById(R.id.et_old_password);
        etNewPasswordl = (EditText)rootView.findViewById(R.id.et_new_password);
        etOldPasswordl.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        etNewPasswordl.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
    }
}