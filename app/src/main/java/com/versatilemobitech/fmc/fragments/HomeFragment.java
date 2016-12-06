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
import com.versatilemobitech.fmc.adapters.GalleryFolderAdapter;
import com.versatilemobitech.fmc.adapters.HomeAdapter;
import com.versatilemobitech.fmc.adapters.NoPostFoundAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.GetPostsModel;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.GetPostsParser;
import com.versatilemobitech.fmc.parsers.PhotoAlbumsParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class HomeFragment extends Fragment implements IAsyncCaller {

    public static final String TAG = "HomeFragment";
    private DashboardActivity mParent;
    private View rootView;

    private ListView list_view;
    private TextView tv_no_posts;
    private HomeAdapter homeAdapter;
    private NoPostFoundAdapter noPostFoundAdapter;
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
        tv_no_posts = (TextView) rootView.findViewById(R.id.tv_no_posts);
        noPostFoundAdapter = new NoPostFoundAdapter(mParent);
        /*homeAdapter = new HomeAdapter(getActivity(), homeDataModels);
        list_view.setAdapter(homeAdapter);*/

        getHomeFeeds("1");


    }

    private void getHomeFeeds(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        GetPostsParser mGetPostsParser = new GetPostsParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.GET_POSTS + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mGetPostsParser);
            Utility.execute(serverIntractorAsync);
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

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof GetPostsModel) {
                    GetPostsModel mGetPostsModel = (GetPostsModel) model;
                    if (mGetPostsModel.getmList() != null && mGetPostsModel.getmList().size() != 0) {
                        //tv_no_posts.setVisibility(View.GONE);
                    } else {
                        //tv_no_posts.setVisibility(View.VISIBLE);
                        list_view.setAdapter(noPostFoundAdapter);
                        setListHeader();
                    }
                }
            }
        }
    }
}
