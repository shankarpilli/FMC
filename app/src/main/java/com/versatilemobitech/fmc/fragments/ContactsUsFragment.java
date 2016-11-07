package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsUsFragment extends Fragment {

    public static final String TAG = "ContactsUsFragment";
    private DashboardActivity mParent;
    private View rootView;

    private LinearLayout ll_share;

    private TextView tv_share;
    private TextView tv_follow_us;
    private TextView tv_web;
    private TextView tv_mail;
    private TextView tv_mobile_num;
    private TextView tv_address;
    private TextView tv_contact_us;

    private ImageView iv_location;
    private ImageView iv_mobile;
    private ImageView iv_mail;
    private ImageView iv_web;
    private ImageView iv_fb;
    private ImageView iv_twitter;
    private ImageView iv_g_plus;
    private ImageView iv_linked_in;
    private ImageView iv_share;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.contacts_us));
        rootView = inflater.inflate(R.layout.fragment_contacts_us, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

        ll_share = (LinearLayout)rootView.findViewById(R.id.ll_share);

        tv_share = (TextView)rootView.findViewById(R.id.tv_share);
        tv_contact_us = (TextView)rootView.findViewById(R.id.tv_contact_us);
        tv_address = (TextView)rootView.findViewById(R.id.tv_address);
        tv_mobile_num = (TextView)rootView.findViewById(R.id.tv_mobile_num);
        tv_mail = (TextView)rootView.findViewById(R.id.tv_mail);
        tv_web = (TextView)rootView.findViewById(R.id.tv_web);
        tv_follow_us = (TextView)rootView.findViewById(R.id.tv_follow_us);

        iv_location = (ImageView) rootView.findViewById(R.id.iv_location);
        iv_share = (ImageView) rootView.findViewById(R.id.iv_share);
        iv_linked_in = (ImageView) rootView.findViewById(R.id.iv_linked_in);
        iv_g_plus = (ImageView) rootView.findViewById(R.id.iv_g_plus);
        iv_twitter = (ImageView) rootView.findViewById(R.id.iv_twitter);
        iv_fb = (ImageView) rootView.findViewById(R.id.iv_fb);
        iv_web = (ImageView) rootView.findViewById(R.id.iv_web);
        iv_mail = (ImageView) rootView.findViewById(R.id.iv_mail);
        iv_mobile = (ImageView) rootView.findViewById(R.id.iv_mobile);

        setTypeFace();
    }

    private void setTypeFace() {

        tv_share.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_contact_us.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_address.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_mobile_num.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_mail.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_web.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_follow_us.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
    }
}