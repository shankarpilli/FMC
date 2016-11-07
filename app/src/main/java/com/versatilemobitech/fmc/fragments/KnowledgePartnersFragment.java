package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class KnowledgePartnersFragment extends Fragment {

    public static final String TAG = "KnowledgePartnersFragment";
    private DashboardActivity mParent;
    private View rootView;

    private LinearLayout ll_knowledge_partners;


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
        ll_knowledge_partners = (LinearLayout) rootView.findViewById(R.id.ll_knowledge_partners);

        for (int i = 0; i < 10; i++) {
            LinearLayout layout_list_header = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.
                    knowledge_patners_item, null);
            LinearLayout ll_items = (LinearLayout) layout_list_header.findViewById(R.id.ll_items);

            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 8f);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 4f);
            lp2.setMargins(15, 15, 15, 15);

            LinearLayout linearLayoutHead = new LinearLayout(getActivity());
            linearLayoutHead.setLayoutParams(lp1);

            for (int j = 0; j < 2; j++) {
                LinearLayout ll = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.knowledge_item, null);
                ll.setLayoutParams(lp2);
                linearLayoutHead.addView(ll);
            }

            ll_items.addView(linearLayoutHead);

            ll_knowledge_partners.addView(layout_list_header);
        }
    }
}