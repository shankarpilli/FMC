package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.HistoryModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.HistoryParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class HistoryFragment extends Fragment implements IAsyncCaller {

    public static final String TAG = "HistoryFragment";
    private HomeActivity mParent;
    private View rootView;

    private TextView txt_history;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent,
                Utility.getResourcesString(getActivity(), R.string.history)));
        rootView = inflater.inflate(R.layout.fragment_history, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        txt_history = (TextView) rootView.findViewById(R.id.txt_history);
        getHistoryData();
    }

    private void getHistoryData() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        HistoryParser mHistoryParser = new HistoryParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.HISTORY, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mHistoryParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    mParent,
                    mParent.getResources().getString(
                            R.string.no_internet_msg),
                    mParent.getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }


    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof HistoryModel) {
                    HistoryModel mHistoryModel = (HistoryModel) model;
                    setHistory(mHistoryModel);
                }
            }
        }
    }

    private void setHistory(HistoryModel mHistoryModel) {
        txt_history.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        txt_history.setText(Html.fromHtml(mHistoryModel.getHistory_text()));
    }

}
