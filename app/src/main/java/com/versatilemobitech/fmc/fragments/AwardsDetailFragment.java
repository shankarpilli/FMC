package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.AwardsDetailListAdapter;
import com.versatilemobitech.fmc.utility.Utility;


public class AwardsDetailFragment extends Fragment {
    public static final String TAG = "AwardsDetailFragment";
    private HomeActivity mParent;
    private View rootView;
    private ListView lv_awards;
    private AwardsDetailListAdapter mAwardsListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle("2016");
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
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