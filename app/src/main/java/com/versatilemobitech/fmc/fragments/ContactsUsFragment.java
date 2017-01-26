package com.versatilemobitech.fmc.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.designs.MaterialDialog;
import com.versatilemobitech.fmc.permissions.Permissions;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsUsFragment extends Fragment {

    public static final String TAG = "ContctUsFragment";
    private HomeActivity mParent;
    private String mToolBarTitle;
    private Toolbar mToolbar;
    private View rootView;

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
        rootView = inflater.inflate(R.layout.contact_fmc, container, false);
        //mParent.txt_our_tour.setText("" + mToolBarTitle);

        initUI(rootView);

        if (Utility.isMarshmallowOS()) {
            Permissions.getInstance().setActivity(getActivity());
            CheckForPermissions(getActivity(), Manifest.permission.CALL_PHONE);
        }

        return rootView;
    }

    private void CheckForPermissions(final Context mContext, final String... mPermisons) {
        // A request for two permissions
        Permissions.getInstance().requestPermissions(new Permissions.IOnPermissionResult() {
            @Override
            public void onPermissionResult(Permissions.ResultSet resultSet) {

                if (resultSet.isPermissionGranted(Manifest.permission.CALL_PHONE)) {
                    // setUi();
                } else {
                    final MaterialDialog denyDialog = new MaterialDialog(mContext, Permissions.TITLE,
                            Permissions.MESSAGE);
                    //Positive
                    denyDialog.setAcceptButton("RE-TRY");
                    denyDialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CheckForPermissions(mContext, mPermisons);
                        }
                    });
                    denyDialog.show();
                }
            }

            @Override
            public void onRationaleRequested(Permissions.IOnRationaleProvided callback, String... permissions) {
                Permissions.getInstance().showRationaleInDialog(Permissions.TITLE,
                        Permissions.MESSAGE, "RE-TRY", callback);
            }
        }, mPermisons);
    }

    private void initUI(View rootView) {

        TextView tv_contact_us = (TextView) rootView.findViewById(R.id.tv_contact_us);
        tv_contact_us.setTypeface(Utility.setTypeFaceRobotoBold(getActivity()));

        TextView tv_address = (TextView) rootView.findViewById(R.id.tv_address);
        tv_address.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));

        TextView tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
        tv_phone.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9247103736"));
                startActivity(intent);
            }
        });

        TextView tv_mail = (TextView) rootView.findViewById(R.id.tv_mail);
        tv_mail.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        tv_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "fmcouncil@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "FM Council");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        TextView tv_site = (TextView) rootView.findViewById(R.id.tv_site);
        tv_site.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        tv_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.ourtour.in";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        TextView tv_share = (TextView) rootView.findViewById(R.id.tv_share);
        tv_share.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));

        TextView tv_follow_us = (TextView) rootView.findViewById(R.id.tv_follow_us);
        tv_follow_us.setTypeface(Utility.setTypeCambriaBoldRegular(getActivity()));

        ImageView iv_yogi = (ImageView) rootView.findViewById(R.id.iv_logo);
        ImageView iv_address = (ImageView) rootView.findViewById(R.id.iv_address);
        ImageView iv_phone = (ImageView) rootView.findViewById(R.id.iv_phone);
        ImageView iv_mail = (ImageView) rootView.findViewById(R.id.iv_mail);
        ImageView iv_site = (ImageView) rootView.findViewById(R.id.iv_site);

        LinearLayout ll_share = (LinearLayout) rootView.findViewById(R.id.ll_share);

        ImageView iv_fb = (ImageView) rootView.findViewById(R.id.iv_fb);
        iv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "https://www.facebook.com/versatile.mobitech/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        ImageView iv_twitter = (ImageView) rootView.findViewById(R.id.iv_twitter);
        iv_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mTwitterUrl = "https://twitter.com/VMobitech";
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mTwitterUrl));
                    startActivity(intent);

                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mTwitterUrl)));
                }
            }
        });
        ImageView iv_google = (ImageView) rootView.findViewById(R.id.iv_google);
        iv_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/105152463775757641701")));
            }
        });
      /*  ImageView iv_you_tube = (ImageView) rootView.findViewById(R.id.iv_you_tube);
        iv_you_tube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //you tube link
            }
        });*/
        ImageView iv_share = (ImageView) rootView.findViewById(R.id.iv_share);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "New version of FMC is available. " + "https://play.google.com/store/apps/details?id=com.versatilemobitech.fmc&hl=en");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "New version of FMC is available. " + "https://play.google.com/store/apps/details?id=com.versatilemobitech.fmc&hl=en");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        ll_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "New version of FMC is available. " + "https://play.google.com/store/apps/details?id=com.versatilemobitech.fmc&hl=en");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }
}