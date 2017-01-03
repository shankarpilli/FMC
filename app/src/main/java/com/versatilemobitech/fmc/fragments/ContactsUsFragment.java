package com.versatilemobitech.fmc.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class ContactsUsFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "ContactsUsFragment";
    private HomeActivity mParent;
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
        mParent = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.getResourcesString(getActivity(), R.string.contacts_us));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_contacts_us, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

        if (Utility.isMarshmallowOS()) {
            Permissions.getInstance().setActivity(getActivity());
            CheckForPermissions(getActivity(), Manifest.permission.CALL_PHONE);
        }

        ll_share = (LinearLayout) rootView.findViewById(R.id.ll_share);

        tv_share = (TextView) rootView.findViewById(R.id.tv_share);
        tv_contact_us = (TextView) rootView.findViewById(R.id.tv_contact_us);
        tv_address = (TextView) rootView.findViewById(R.id.tv_address);
        tv_mobile_num = (TextView) rootView.findViewById(R.id.tv_mobile_num);
        tv_mail = (TextView) rootView.findViewById(R.id.tv_mail);
        tv_web = (TextView) rootView.findViewById(R.id.tv_web);
        tv_follow_us = (TextView) rootView.findViewById(R.id.tv_follow_us);

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

        iv_location.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        iv_linked_in.setOnClickListener(this);
        iv_g_plus.setOnClickListener(this);
        iv_twitter.setOnClickListener(this);
        iv_fb.setOnClickListener(this);
        iv_web.setOnClickListener(this);
        iv_mail.setOnClickListener(this);
        iv_mobile.setOnClickListener(this);

        tv_share.setOnClickListener(this);
        tv_contact_us.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        tv_mobile_num.setOnClickListener(this);
        tv_mail.setOnClickListener(this);
        tv_web.setOnClickListener(this);
        tv_follow_us.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_share:
            case R.id.tv_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "FMC");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.iv_web:
            case R.id.tv_web:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
                break;
            case R.id.iv_mail:
            case R.id.tv_mail:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "daduvainaveenkumar@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ourtour");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
            case R.id.iv_mobile:
            case R.id.tv_mobile_num:
                Intent intentDail = new Intent(Intent.ACTION_DIAL);
                intentDail.setData(Uri.parse("tel:"+tv_mobile_num.getText().toString().trim()));
                startActivity(intentDail);
                break;
            case R.id.iv_fb:
                String uri = "https://www.facebook.com/ourtour.georace.1";
                Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(fbIntent);
                break;
            case R.id.iv_g_plus:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/104074863268189065180")));
                break;
            case R.id.iv_linked_in:
                Intent linkedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://you"));
                final PackageManager packageManager = getContext().getPackageManager();
                final List<ResolveInfo> list = packageManager.queryIntentActivities(linkedIntent, PackageManager.MATCH_DEFAULT_ONLY);
                if (list.isEmpty()) {
                    linkedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=you"));
                }
                startActivity(linkedIntent);
                break;
            case R.id.iv_twitter:
                String mTwitterUrl = "https://twitter.com/ourtour9";
                try {
                    Intent twitterIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mTwitterUrl));
                    startActivity(twitterIntent);

                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mTwitterUrl)));
                }
                break;
        }
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
}