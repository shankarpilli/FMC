package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.EditorialsAdapter;
import com.versatilemobitech.fmc.adapters.MembersAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.EditorialsModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.EditorialParser;
import com.versatilemobitech.fmc.parsers.EventsParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class EditorialsFragment extends Fragment implements IAsyncCaller {

    public static final String TAG = "EditorialsFragment";
    private DashboardActivity mParent;
    private View rootView;
    private ListView ll_editorials;
    private EditorialsAdapter editorialsAdapter;
    private ArrayList<EditorialsModel> editorialsModels;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.editorials));
        rootView = inflater.inflate(R.layout.fragment_editorials, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        getEditorialDataFromApi("1");
        ll_editorials = (ListView) rootView.findViewById(R.id.ll_editorials);
        editorialsAdapter = new EditorialsAdapter(getActivity(), editorialsModels);
        ll_editorials.setAdapter(editorialsAdapter);
    }

    public void getEditorialDataFromApi(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        EditorialParser mEditorialParser = new EditorialParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.GET_EDITORIAL + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mEditorialParser);
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

    }
}