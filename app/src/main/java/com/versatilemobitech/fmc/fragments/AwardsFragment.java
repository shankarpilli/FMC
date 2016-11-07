package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.AwardsAdapter;
import com.versatilemobitech.fmc.utility.Utility;


public class AwardsFragment extends Fragment {
    public static final String TAG = "AwardsFragment";
    private DashboardActivity mParent;
    private View rootView;
private AwardsAdapter mAwardsAdapter;
private GridView grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.awards));
        rootView = inflater.inflate(R.layout.fragment_awards, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

        grid = (GridView)rootView.findViewById(R.id.grid);
        mAwardsAdapter = new AwardsAdapter(mParent);
        grid.setAdapter(mAwardsAdapter);
    }
}