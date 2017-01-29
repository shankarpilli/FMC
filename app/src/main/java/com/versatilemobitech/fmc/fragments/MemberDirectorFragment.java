package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.MembersAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.MembersModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.MembersParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class MemberDirectorFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener {

    public static final String TAG = "MemberDirectorFragment";
    private HomeActivity mParent;
    private View rootView;

    private EditText edt_search;
    private TextView tv_no_members;
    private ListView ll_search;
    private MembersAdapter membersAdapter;
    private ArrayList<MembersModel> membersModels;
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
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent,
                Utility.getResourcesString(getActivity(), R.string.member_directory)));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_member_director, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        edt_search = (EditText) rootView.findViewById(R.id.edt_search);
        tv_no_members = (TextView) rootView.findViewById(R.id.tv_no_members);
        ll_search = (ListView) rootView.findViewById(R.id.ll_search);
        ll_search.setAdapter(membersAdapter);
        edt_search.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        tv_no_members.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));


        edt_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                Utility.showLog("CharSequence", "CharSequence" + arg0);
                membersAdapter.getFilter().filter(arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        getMembersData("1");
    }

    private void getMembersData(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        MembersParser mMembersParser = new MembersParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.GET_MEMBERS + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mMembersParser);
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
                if (model instanceof MembersModel) {
                    MembersModel mMembersModel = (MembersModel) model;
                    if (membersModels == null) {
                        if (mMembersModel.getMembersModels() == null) {
                            tv_no_members.setVisibility(View.VISIBLE);
                            ll_search.setVisibility(View.GONE);
                        } else {
                            tv_no_members.setVisibility(View.GONE);
                            ll_search.setVisibility(View.VISIBLE);
                            if (membersModels == null) {
                                membersModels = new ArrayList<MembersModel>();
                            }
                            membersModels.addAll(mMembersModel.getMembersModels());
                            if (membersAdapter == null) {
                                setGridData();
                            }
                        }
                    } else {
                        ll_search.setVisibility(View.VISIBLE);
                        tv_no_members.setVisibility(View.GONE);
                        if (mMembersModel.getMembersModels() != null && mMembersModel.getMembersModels().size() > 0) {
                            membersModels.addAll(mMembersModel.getMembersModels());
                            if (membersAdapter == null) {
                                setGridData();
                            } else {
                                membersAdapter.notifyDataSetChanged();
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
        membersAdapter = new MembersAdapter(getActivity(), membersModels);
        ll_search.setAdapter(membersAdapter);
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
                getMembersData("" + mPageNumber);
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
            if (ll_search.getAdapter() != null) {
                if (ll_search.getLastVisiblePosition() == ll_search.getAdapter().getCount() - 1 &&
                        ll_search.getChildAt(ll_search.getChildCount() - 1).getBottom() <= ll_search.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }
}