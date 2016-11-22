package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.HomeAdapter;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    private DashboardActivity mParent;
    private View rootView;

    private ListView list_view;
    private HomeAdapter homeAdapter;
    private ArrayList<HomeDataModel> homeDataModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.home));
        mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        homeDataModels = new ArrayList<>();
        list_view = (ListView) rootView.findViewById(R.id.list_view);
        /*homeAdapter = new HomeAdapter(getActivity(), homeDataModels);
        list_view.setAdapter(homeAdapter);*/

        getHomeFeeds();


    }

    private void getHomeFeeds() {
        if (Utility.isNetworkAvailable(getActivity())) {

        } else {
            Utility.showSettingDialog(
                    getActivity(),
                    getActivity().getResources().getString(
                            R.string.no_internet_msg),
                    getActivity().getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }

    private void setListHeader() {
        LinearLayout layout_list_header = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.
                home_post_your_topic, null);
        final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (Integer.parseInt("170") * scale + 0.5f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.
                LayoutParams.MATCH_PARENT, pixels);
        params.setMargins(15, 15, 15, 15);
        layout_list_header.setLayoutParams(params);
        TextView tv_post = (TextView) layout_list_header.findViewById(R.id.tv_post);
        tv_post.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        TextView txt_post_your_topic = (TextView) layout_list_header.findViewById(R.id.txt_post_your_topic);
        txt_post_your_topic.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        EditText et_what_is_on_u_mind = (EditText) layout_list_header.findViewById(R.id.et_what_is_on_u_mind);
        et_what_is_on_u_mind.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        list_view.addHeaderView(layout_list_header);
    }
}
