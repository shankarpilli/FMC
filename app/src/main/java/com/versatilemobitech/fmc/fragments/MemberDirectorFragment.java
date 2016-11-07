package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.MembersAdapter;
import com.versatilemobitech.fmc.models.MembersModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class MemberDirectorFragment extends Fragment {

    public static final String TAG = "MemberDirectorFragment";
    private DashboardActivity mParent;
    private View rootView;

    private ListView ll_search;
    private MembersAdapter membersAdapter;
    private ArrayList<MembersModel> membersModels;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.member_directory));
        rootView = inflater.inflate(R.layout.fragment_member_director, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        ll_search = (ListView) rootView.findViewById(R.id.ll_search);
        membersAdapter = new MembersAdapter(getActivity(), membersModels);
        ll_search.setAdapter(membersAdapter);
    }
}