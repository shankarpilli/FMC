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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.designs.MaterialDialog;
import com.versatilemobitech.fmc.permissions.Permissions;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditorialPdfReaderFragment extends Fragment {

    public static final String TAG = "EditorialPdfReaderFragment";
    private DashboardActivity mParent;
    private View rootView;

    private String mPdfTitle;
    private String mPdfPath;
    private String mFrom;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();

        Bundle mBundle = getArguments();
        mPdfPath = mBundle.getString("pdfUrl");
        mPdfTitle = mBundle.getString("pdfTitle");
        mFrom = mBundle.getString("from");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(mPdfTitle);
        rootView = inflater.inflate(R.layout.fragment_webview_pdf, container, false);

        WebView mWebView = (WebView)rootView.findViewById(R.id.web_pdf_view);
        mWebView.loadUrl(mPdfPath);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        return rootView;

    }


}