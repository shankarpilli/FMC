package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.AwardsAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.AwardsYearModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.AwardsYearParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class AwardsFragment extends Fragment implements IAsyncCaller, AdapterView.OnItemClickListener {

    public static final String TAG = "AwardsFragment";
    private HomeActivity mParent;
    private View rootView;

    private GridView grid_view;
    private TextView tv_no_awards;
    private AwardsAdapter awardsAdapter;
    private ArrayList<AwardsYearModel> awardDetailsModels;

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
        if (rootView!=null){
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_awards, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        grid_view = (GridView) rootView.findViewById(R.id.grid_view);
        tv_no_awards = (TextView) rootView.findViewById(R.id.tv_no_awards);
        grid_view.setOnItemClickListener(this);

        getAwardsFromApi();
    }

    private void getAwardsFromApi() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        AwardsYearParser mAwardsYearParser = new AwardsYearParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.AWARD_YEARS, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mAwardsYearParser);
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
                if (model instanceof AwardsYearModel) {
                    AwardsYearModel awardsYearModel = (AwardsYearModel) model;
                    awardDetailsModels = awardsYearModel.getAwardsYearModels();
                    setGridData();
                }
            }
        }
    }

    private void setGridData() {
        awardsAdapter = new AwardsAdapter(getActivity(), awardDetailsModels);
        grid_view.setAdapter(awardsAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        AwardsYearModel mAwardsYearModel = awardDetailsModels.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("year", mAwardsYearModel.getYear());
        Utility.navigateDashBoardFragment(new AwardsDetialsFragment(), AwardsDetialsFragment.TAG, bundle, mParent);
    }
}