package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * Created by Manoj on 12/22/2016.
 */
public class EditorialPdfReaderFragment extends Fragment {
    public static final String TAG = "WebViewFragment";
    private DashboardActivity mParent;
    private View rootView;
    private WebView mWebView;
    private String url;
    private String pdf_title;
    private String from;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        Bundle bundle = getArguments();
        url = bundle.getString("file_url");
        pdf_title = bundle.getString("pdfTitle");
        from = bundle.getString("from");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.editorials));
        mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_webview, container, false);
        initUI();
        return rootView;
    }

    private void initUI() {
        mWebView = (WebView) rootView.findViewById(R.id.webview);
        if (url != null && !url.equals("") && !url.equals("null") && url.length() > 0) {
            Utility.showProgressDialog(mParent, "Loading...", false);
            mWebView.setBackgroundColor(0x00000000);
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setDomStorageEnabled(true);
            mWebView.setVerticalScrollBarEnabled(true);
            mWebView.setHorizontalScrollBarEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setSupportMultipleWindows(true);
            mWebView.setWebChromeClient(new WebChromeClient());
            mWebView.setVerticalScrollBarEnabled(true);
            mWebView.setHorizontalScrollBarEnabled(true);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view,
                                                        String url) {
                    if (url.endsWith(".pdf") || url.endsWith(".doc") || url.endsWith(".docx")) {
                        String googleDocs = "https://docs.google.com/viewer?url=";
                        view.loadUrl(googleDocs + url);
                    } else {
                        if (mParent.progressDialog != null) {
                            Utility.showProgressDialog(mParent, "Loading...", false);
                        }
                        view.loadUrl(url);
                    }
                    return true;
                }


                @Override
                public void onPageFinished(WebView view, final String url) {
                    super.onPageFinished(view, url);
                    if (mParent.progressDialog != null)
                        mParent.progressDialog.dismiss();
                }
            });

            if (url.endsWith(".pdf") || url.endsWith(".doc") || url.endsWith(".docx")) {
                String googleDocs = "https://docs.google.com/viewer?url=";
                mWebView.loadUrl(googleDocs + url);
            } else
                mWebView.loadUrl(url);

            mWebView.setWebChromeClient(new WebChromeClient());
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
        mWebView.pauseTimers(); //careful with this! Pauses all layout,
        //parsing, and JavaScript timers for all WebViews.
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        mWebView.resumeTimers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.loadUrl("about:blank");
        mWebView.stopLoading();
        mWebView.setWebChromeClient(null);
        mWebView.setWebViewClient(null);
        mWebView.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    mWebView.destroy();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 3000);
        mWebView = null;
    }

}