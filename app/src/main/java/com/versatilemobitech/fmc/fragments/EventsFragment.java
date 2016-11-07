package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.EventsAdapter;
import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class EventsFragment extends Fragment {

    public static final String TAG = "EventsFragment";
    private DashboardActivity mParent;
    private View rootView;
    private ListView lv_events;

private EventsAdapter mEventsAdapter=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.events));
        rootView = inflater.inflate(R.layout.fragment_events, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

        lv_events = (ListView)rootView.findViewById(R.id.lv_events);
ArrayList<EventsModel> models = eventModels();
    mEventsAdapter = new EventsAdapter(mParent,models);
        lv_events.setAdapter(mEventsAdapter);
    }

    private ArrayList<EventsModel> eventModels() {
        ArrayList<EventsModel> mList = new ArrayList<>();

        for(int i=0;i<5;i++){
            EventsModel mEventsModel = new EventsModel();
            mEventsModel.setmChiefGuest("sri.k. tharaka ramarao");
            mEventsModel.setmContact("90322793265");
            mEventsModel.setmDateOfEvent("28-10-2016");
            mEventsModel.setmDetails("this meeting for launching the app");
            mEventsModel.setmOrganizedBy("INCP Hyderabad Branch");
            mEventsModel.setmVenue("Hyderabad");
            mEventsModel.setVisible(false);

            mList.add(mEventsModel);
        }
        return mList;

    }
}
