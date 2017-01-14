package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.AwardsDetailsAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.AwardDetailsModel;
import com.versatilemobitech.fmc.models.AwardListModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.AwardsParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class AwardsDetialsFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener  {

    public static final String TAG = "AwardsDetialsFragment";
    private HomeActivity mParent;
    private View rootView;
    private ListView lv_awards;
    private TextView tv_no_awards;
    private ArrayList<AwardDetailsModel> awardDetailsModels;

    private AwardsDetailsAdapter mAwardsDetailsAdapter = null;
    private Bundle bundle;
    private String mString;

    private int aaTotalCount, aaVisibleCount, aaFirstVisibleItem;
    private boolean endScroll = false;
    private int mPageNumber = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        bundle = getArguments();
        mString = bundle.getString("year");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(""+mString);
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_awards_details, container, false);
        initUI();
        return rootView;
    }

    private void initUI() {
        lv_awards = (ListView) rootView.findViewById(R.id.lv_awards);
        tv_no_awards = (TextView) rootView.findViewById(R.id.tv_no_awards);
        getAwards("1");
    }

    public void getAwards(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        AwardsParser mAwardsParser = new AwardsParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.AWARDS + mString + "/" + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mAwardsParser);
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
                if (model instanceof AwardListModel) {
                    AwardListModel mAwardListModel = (AwardListModel) model;
                    if (awardDetailsModels == null) {
                        if (mAwardListModel.getmAwardListModel() == null) {
                            tv_no_awards.setVisibility(View.VISIBLE);
                            lv_awards.setVisibility(View.GONE);
                        } else {
                            tv_no_awards.setVisibility(View.GONE);
                            lv_awards.setVisibility(View.VISIBLE);
                            if (awardDetailsModels == null) {
                                awardDetailsModels = new ArrayList<AwardDetailsModel>();
                            }
                            awardDetailsModels.addAll(mAwardListModel.getmAwardListModel());
                            if (mAwardsDetailsAdapter == null) {
                                setListData();
                            }
                        }
                    } else {
                        lv_awards.setVisibility(View.VISIBLE);
                        tv_no_awards.setVisibility(View.GONE);
                        if (mAwardListModel.getmAwardListModel() != null && mAwardListModel.getmAwardListModel().size() > 0) {
                            awardDetailsModels.addAll(mAwardListModel.getmAwardListModel());
                            if (mAwardsDetailsAdapter == null) {
                                setListData();
                            } else {
                                mAwardsDetailsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            endScroll = true;
                        }
                    }
                }
            }
        }
    }


    private void setListData() {
        mAwardsDetailsAdapter = new AwardsDetailsAdapter(getActivity(), awardDetailsModels);
        lv_awards.setAdapter(mAwardsDetailsAdapter);
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
                getAwards("" + mPageNumber);
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
            if (lv_awards.getAdapter() != null) {
                if (lv_awards.getLastVisiblePosition() == lv_awards.getAdapter().getCount() - 1 &&
                        lv_awards.getChildAt(lv_awards.getChildCount() - 1).getBottom() <= lv_awards.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }
}
