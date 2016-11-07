package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.AwardsDetailListAdapter;


public class AwardsDetailFragment extends Fragment {
    public static final String TAG = "AwardsDetailFragment";
    private DashboardActivity mParent;
    private View rootView;
    private ListView lv_awards;
    private AwardsDetailListAdapter mAwardsListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText("2016");
        rootView = inflater.inflate(R.layout.fragment_awards_item, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        lv_awards = (ListView)rootView.findViewById(R.id.lv_awards);
        mAwardsListAdapter = new AwardsDetailListAdapter(mParent);
        lv_awards.setAdapter(mAwardsListAdapter);
    }
}