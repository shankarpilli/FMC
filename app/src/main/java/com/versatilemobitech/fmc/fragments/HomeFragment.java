package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.HomeAdapter;
import com.versatilemobitech.fmc.adapters.NoPostFoundAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.CommentsModel;
import com.versatilemobitech.fmc.models.GetPostsModel;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.CommentsParser;
import com.versatilemobitech.fmc.parsers.GetPostsParser;
import com.versatilemobitech.fmc.parsers.PostDataParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class HomeFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener {

    public static final String TAG = "HomeFragment";
    private DashboardActivity mParent;
    private View rootView;

    private ListView list_view;
    private TextView tv_no_posts;
    private HomeAdapter homeAdapter;
    private NoPostFoundAdapter noPostFoundAdapter;
    private ArrayList<HomeDataModel> homeDataModels;
    private boolean isDocSelected;
    private boolean isPdfSelected;
    private boolean isImgSelected;


    private int aaTotalCount, aaVisibleCount, aaFirstVisibleItem;
    private boolean endScroll = false;
    private int mPageNumber = 1;

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
        list_view = (ListView) rootView.findViewById(R.id.list_view);
        tv_no_posts = (TextView) rootView.findViewById(R.id.tv_no_posts);
        noPostFoundAdapter = new NoPostFoundAdapter(mParent);
        /*homeAdapter = new HomeAdapter(getActivity(), homeDataModels);
        list_view.setAdapter(homeAdapter);*/

        getHomeFeeds("1");
        list_view.setOnScrollListener(this);

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
        LinearLayout ll_post = (LinearLayout) layout_list_header.findViewById(R.id.ll_post);
        TextView tv_post = (TextView) layout_list_header.findViewById(R.id.tv_post);
        tv_post.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        TextView txt_post_your_topic = (TextView) layout_list_header.findViewById(R.id.txt_post_your_topic);
        txt_post_your_topic.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        final EditText et_what_is_on_u_mind = (EditText) layout_list_header.findViewById(R.id.et_what_is_on_u_mind);
        et_what_is_on_u_mind.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        list_view.addHeaderView(layout_list_header);
        ll_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFields(et_what_is_on_u_mind)) {
                    postFeed(et_what_is_on_u_mind);
                }
            }
        });
    }

    private void postFeed(EditText et_what_is_on_u_mind) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("post_text", "" + et_what_is_on_u_mind.getText().toString());
        paramMap.put("user_id", Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID));
        PostDataParser mPostDataParser = new PostDataParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.SAVE_POST, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mPostDataParser);
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

    private boolean isValidFields(EditText et_what_is_on_u_mind) {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_what_is_on_u_mind.getText().toString().trim()) && !isDocSelected && !isImgSelected && !isPdfSelected) {
            Utility.setSnackBarEnglish(mParent, et_what_is_on_u_mind, "Please enter what is on ur mind");
        } else {
            isValidated = true;
        }
        return isValidated;
    }


    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof GetPostsModel) {
                    GetPostsModel mGetPostsModel = (GetPostsModel) model;
                    if (homeDataModels == null) {
                        if (mGetPostsModel.getmList() == null) {
                            /*tv_no_posts.setVisibility(View.VISIBLE);
                            list_view.setVisibility(View.GONE);*/
                            list_view.setAdapter(noPostFoundAdapter);
                            setListHeader();
                        } else {
                            tv_no_posts.setVisibility(View.GONE);
                            list_view.setVisibility(View.VISIBLE);
                            if (homeDataModels == null) {
                                homeDataModels = new ArrayList<HomeDataModel>();
                            }
                            homeDataModels.addAll(mGetPostsModel.getmList());
                            if (homeAdapter == null) {
                                setListData();
                            }
                        }
                    } else {
                        list_view.setVisibility(View.VISIBLE);
                        tv_no_posts.setVisibility(View.GONE);
                        if (mGetPostsModel.getmList() != null && mGetPostsModel.getmList().size() > 0) {
                            homeDataModels.addAll(mGetPostsModel.getmList());
                            if (homeAdapter == null) {
                                setListData();
                            } else {
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            endScroll = true;
                        }
                    }
                } else if (model instanceof CommentsModel) {
                    CommentsModel mCommentsModel = (CommentsModel) model;
                    Utility.showToastMessage(mParent, mCommentsModel.getMessage());
                }
            }
        }
    }

    private void setListData() {
        homeAdapter = new HomeAdapter(mParent, getActivity(), this, homeDataModels);
        list_view.setAdapter(homeAdapter);
        setListHeader();
    }

    public void commentOnPost(int position, String message) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("post_id", homeDataModels.get(position).getPost_id());
        paramMap.put("user_id", Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID));
        paramMap.put("comment", message);
        CommentsParser mCommentsParser = new CommentsParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.POST_COMMENT, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mCommentsParser);
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            isScrollCompleted();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        aaTotalCount = totalItemCount;
        aaVisibleCount = visibleItemCount;
        aaFirstVisibleItem = firstVisibleItem;
    }

    private void isScrollCompleted() {
        if (aaTotalCount == (aaFirstVisibleItem + aaVisibleCount) && !endScroll) {
            if (Utility.isNetworkAvailable(getActivity())) {
                mPageNumber = mPageNumber + 1;
                getHomeFeeds("" + mPageNumber);
                Utility.showLog("mPageNumber", "mPageNumber : " + mPageNumber);
            } else {
                Utility.showSettingDialog(
                        getActivity(),
                        getActivity().getResources().getString(
                                R.string.no_internet_msg),
                        getActivity().getResources().getString(
                                R.string.no_internet_title),
                        Utility.NO_INTERNET_CONNECTION).show();
            }
        } else {
            if (list_view.getAdapter() != null) {
                if (list_view.getLastVisiblePosition() == list_view.getAdapter().getCount() - 1 &&
                        list_view.getChildAt(list_view.getChildCount() - 1).getBottom() <= list_view.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }
}
