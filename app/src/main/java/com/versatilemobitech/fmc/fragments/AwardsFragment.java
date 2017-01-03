package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.AwardsAdapter;
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


public class AwardsFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener {

    public static final String TAG = "AwardsFragment";
    private HomeActivity mParent;
    private View rootView;

    private GridView grid_view;
    private TextView tv_no_awards;
    private AwardsAdapter awardsAdapter;
    private ArrayList<AwardDetailsModel> awardDetailsModels;

    private int aaTotalCount, aaVisibleCount, aaFirstVisibleItem;
    private boolean endScroll = false;
    private int mPageNumber = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.getResourcesString(getActivity(), R.string.awards));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_awards, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        grid_view = (GridView) rootView.findViewById(R.id.grid_view);
        tv_no_awards = (TextView) rootView.findViewById(R.id.tv_no_awards);
        /*grid_view.setOnItemClickListener(this);*/

        getAwardsFromApi("1");
        grid_view.setOnScrollListener(this);
    }

    private void getAwardsFromApi(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        AwardsParser mAwardsParser = new AwardsParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.AWARDS + mPageNumber, paramMap,
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
                            grid_view.setVisibility(View.GONE);
                        } else {
                            tv_no_awards.setVisibility(View.GONE);
                            grid_view.setVisibility(View.VISIBLE);
                            if (awardDetailsModels == null) {
                                awardDetailsModels = new ArrayList<AwardDetailsModel>();
                            }
                            awardDetailsModels.addAll(mAwardListModel.getmAwardListModel());
                            if (awardsAdapter == null) {
                                setGridData();
                            }
                        }
                    } else {
                        grid_view.setVisibility(View.VISIBLE);
                        tv_no_awards.setVisibility(View.GONE);
                        if (mAwardListModel.getmAwardListModel() != null && mAwardListModel.getmAwardListModel().size() > 0) {
                            awardDetailsModels.addAll(mAwardListModel.getmAwardListModel());
                            if (awardsAdapter == null) {
                                setGridData();
                            } else {
                                awardsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            endScroll = true;
                        }
                    }
                }
            }
        }
    }

    private void setGridData() {
        awardsAdapter = new AwardsAdapter(getActivity(), awardDetailsModels);
        grid_view.setAdapter(awardsAdapter);
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
                getAwardsFromApi("" + mPageNumber);
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
            if (grid_view.getAdapter() != null) {
                if (grid_view.getLastVisiblePosition() == grid_view.getAdapter().getCount() - 1 &&
                        grid_view.getChildAt(grid_view.getChildCount() - 1).getBottom() <= grid_view.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }

}