package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.EditorialsAdapter;
import com.versatilemobitech.fmc.adapters.HomeAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.EditorialsModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.EditorialParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class EditorialsFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener {

    public static final String TAG = "EditorialsFragment";
    private HomeActivity mParent;
    private View rootView;
    private ListView ll_editorials;
    private TextView tv_no_editorials;
    private EditorialsAdapter editorialsAdapter;
    private ArrayList<EditorialsModel> editorialsModels;

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
                Utility.getResourcesString(getActivity(), R.string.editorials)));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_editorials, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        getEditorialDataFromApi("1");
        ll_editorials = (ListView) rootView.findViewById(R.id.ll_editorials);
        tv_no_editorials = (TextView) rootView.findViewById(R.id.tv_no_editorials);
        // editorialsAdapter = new EditorialsAdapter(getActivity(), editorialsModels);
        ll_editorials.setAdapter(editorialsAdapter);
        ll_editorials.setOnScrollListener(this);
        ll_editorials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditorialsModel mEditorialsModel = editorialsModels.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("from", EditorialsFragment.TAG);
                bundle.putString("file_url", mEditorialsModel.getBook_path());
                bundle.putString("pdfTitle", mEditorialsModel.getBook_name());
                Utility.navigateDashBoardFragment(new EditorialPdfReaderFragment(), EditorialPdfReaderFragment.TAG, bundle, mParent);
            }
        });
    }

    public void getEditorialDataFromApi(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        EditorialParser mEditorialParser = new EditorialParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.GET_EDITORIAL + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mEditorialParser);
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
                if (model instanceof EditorialsModel) {
                    EditorialsModel mEditorialsModel = (EditorialsModel) model;
                    if (editorialsModels == null) {
                        if (mEditorialsModel.getEditorialsModels() == null) {
                            tv_no_editorials.setVisibility(View.VISIBLE);
                            ll_editorials.setVisibility(View.GONE);
                        } else {
                            tv_no_editorials.setVisibility(View.GONE);
                            ll_editorials.setVisibility(View.VISIBLE);
                            if (editorialsModels == null) {
                                editorialsModels = new ArrayList<EditorialsModel>();
                            }
                            editorialsModels.addAll(mEditorialsModel.getEditorialsModels());
                            if (editorialsAdapter == null) {
                                setListData();
                            }
                        }
                    } else {
                        ll_editorials.setVisibility(View.VISIBLE);
                        tv_no_editorials.setVisibility(View.GONE);
                        if (mEditorialsModel.getEditorialsModels() != null && mEditorialsModel.getEditorialsModels().size() > 0) {
                            editorialsModels.addAll(mEditorialsModel.getEditorialsModels());
                            if (editorialsAdapter == null) {
                                setListData();
                            } else {
                                editorialsAdapter.notifyDataSetChanged();
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
        editorialsAdapter = new EditorialsAdapter(getActivity(), editorialsModels);
        ll_editorials.setAdapter(editorialsAdapter);
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
                getEditorialDataFromApi("" + mPageNumber);
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
            if (ll_editorials.getAdapter() != null) {
                if (ll_editorials.getLastVisiblePosition() == ll_editorials.getAdapter().getCount() - 1 &&
                        ll_editorials.getChildAt(ll_editorials.getChildCount() - 1).getBottom() <= ll_editorials.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }
}