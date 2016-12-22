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
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.EditorialsAdapter;
import com.versatilemobitech.fmc.adapters.KnowledgePartnersAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.KnowledgePartnerItemModel;
import com.versatilemobitech.fmc.models.KnowledgePartnersModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.GetKnowledgePartnersParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class KnowledgePartnersFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener {

    public static final String TAG = "KnowledgePartnersFragment";
    private DashboardActivity mParent;
    private View rootView;

    private ListView ll_knowledge_partners;
    private TextView tv_no_knowledge_partners;
    private ArrayList<KnowledgePartnerItemModel> knowledgePartnerItemModels;

    private int aaTotalCount, aaVisibleCount, aaFirstVisibleItem;
    private boolean endScroll = false;
    private int mPageNumber = 1;

    private KnowledgePartnersAdapter knowledgePartnersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.knowledge_partners));
        rootView = inflater.inflate(R.layout.fragment_knowledge_partners, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        ll_knowledge_partners = (ListView) rootView.findViewById(R.id.ll_knowledge_partners);
        tv_no_knowledge_partners = (TextView) rootView.findViewById(R.id.tv_no_knowledge_partners);
        tv_no_knowledge_partners.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        getKnowledgePartner("1");
    }

    private void getKnowledgePartner(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        GetKnowledgePartnersParser mGetKnowledgePartnersParser = new GetKnowledgePartnersParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.KNOWLEDGE_PARTNERS + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mGetKnowledgePartnersParser);
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
                if (model instanceof KnowledgePartnersModel) {
                    KnowledgePartnersModel mKnowledgePartnersModel = (KnowledgePartnersModel) model;
                    if (knowledgePartnerItemModels == null) {
                        if (mKnowledgePartnersModel.getKnowledgePartnerItemModels() == null) {
                            tv_no_knowledge_partners.setVisibility(View.VISIBLE);
                            ll_knowledge_partners.setVisibility(View.GONE);
                        } else {
                            tv_no_knowledge_partners.setVisibility(View.GONE);
                            ll_knowledge_partners.setVisibility(View.VISIBLE);
                            if (knowledgePartnerItemModels == null) {
                                knowledgePartnerItemModels = new ArrayList<KnowledgePartnerItemModel>();
                            }
                            knowledgePartnerItemModels.addAll(mKnowledgePartnersModel.getKnowledgePartnerItemModels());
                            if (knowledgePartnersAdapter == null) {
                                setListData();
                            }
                        }
                    } else {
                        ll_knowledge_partners.setVisibility(View.VISIBLE);
                        tv_no_knowledge_partners.setVisibility(View.GONE);
                        if (mKnowledgePartnersModel.getKnowledgePartnerItemModels() != null && mKnowledgePartnersModel.getKnowledgePartnerItemModels().size() > 0) {
                            knowledgePartnerItemModels.addAll(mKnowledgePartnersModel.getKnowledgePartnerItemModels());
                            if (knowledgePartnersAdapter == null) {
                                setListData();
                            } else {
                                knowledgePartnersAdapter.notifyDataSetChanged();
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
        knowledgePartnersAdapter = new KnowledgePartnersAdapter(getActivity(), knowledgePartnerItemModels);
        ll_knowledge_partners.setAdapter(knowledgePartnersAdapter);
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
                getKnowledgePartner("" + mPageNumber);
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
            if (ll_knowledge_partners.getAdapter() != null) {
                if (ll_knowledge_partners.getLastVisiblePosition() == ll_knowledge_partners.getAdapter().getCount() - 1 &&
                        ll_knowledge_partners.getChildAt(ll_knowledge_partners.getChildCount() - 1).getBottom() <= ll_knowledge_partners.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }
}