package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.fragments.EventsFragment;
import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 06-11-2016.
 */

public class EventsAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<EventsModel> mEventsModelList;
    private EventsModel mEventsModel;

    public EventsAdapter(Context mParent, ArrayList<EventsModel> models) {
        this.mContext = mParent;
        this.mEventsModelList = models;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mEventsModelList.size();
    }

    @Override
    public EventsModel getItem(int position) {
        return mEventsModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListEventsHolder mListEventsHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_events,
                    null);
            mListEventsHolder = new ListEventsHolder();
            convertView.setTag(mListEventsHolder);
        } else {
            mListEventsHolder = (ListEventsHolder) convertView.getTag();
        }
        mEventsModel = (EventsModel) getItem(position);
        mListEventsHolder.iv_down = (ImageView) convertView.findViewById(R.id.iv_down);
        mListEventsHolder.iv_up = (ImageView) convertView.findViewById(R.id.iv_up);
        mListEventsHolder.tv_more_details = (TextView) convertView.findViewById(R.id.tv_more_details);
        mListEventsHolder.ll_more_details = (LinearLayout) convertView.findViewById(R.id.ll_more_details);
        mListEventsHolder.rl_more_details = (RelativeLayout) convertView.findViewById(R.id.rl_more_details);

        final ListEventsHolder finalMListEventsHolder = mListEventsHolder;
        final ListEventsHolder finalMListEventsHolder1 = mListEventsHolder;
        mListEventsHolder.rl_more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEventsModel.isVisible()){
                    finalMListEventsHolder.iv_up.setVisibility(View.GONE);
                    finalMListEventsHolder.iv_down.setVisibility(View.VISIBLE);
                    finalMListEventsHolder1.ll_more_details.setVisibility(View.GONE);
                    mEventsModel.setVisible(false);
                    finalMListEventsHolder1.tv_more_details.setText("More Details");
                }else {
                    finalMListEventsHolder.iv_up.setVisibility(View.VISIBLE);
                    finalMListEventsHolder.iv_down.setVisibility(View.GONE);
                    finalMListEventsHolder1.ll_more_details.setVisibility(View.VISIBLE);
                    mEventsModel.setVisible(true);
                    finalMListEventsHolder1.tv_more_details.setText("Less Details");
                }

            }
        });
        return convertView;
    }





    private class ListEventsHolder {

        private ImageView iv_down;
        private ImageView iv_up;
        private TextView tv_more_details;
        private LinearLayout ll_more_details;
        private RelativeLayout rl_more_details;
    }
}
