package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.fragments.EventsFragment;
import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 06-11-2016.
 */

public class EventsAdapter extends BaseAdapter {

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
        mListEventsHolder.pie_events = (PieChart) convertView.findViewById(R.id.pie_events);

        mListEventsHolder.tv_venu = (TextView) convertView.findViewById(R.id.tv_venu);
        mListEventsHolder.tv_organized_by = (TextView) convertView.findViewById(R.id.tv_organized_by);
        mListEventsHolder.tv_contact = (TextView) convertView.findViewById(R.id.tv_contact);
        mListEventsHolder.tv_chief_guest = (TextView) convertView.findViewById(R.id.tv_chief_guest);
        mListEventsHolder.tv_date_event = (TextView) convertView.findViewById(R.id.tv_date_event);
        mListEventsHolder.tv_details = (TextView) convertView.findViewById(R.id.tv_details);

        mListEventsHolder.tv_venu.setText("" + mEventsModel.getmVenue());
        mListEventsHolder.tv_organized_by.setText("" + mEventsModel.getmOrganizedBy());
        mListEventsHolder.tv_contact.setText("" + mEventsModel.getmContact());
        mListEventsHolder.tv_chief_guest.setText("" + mEventsModel.getmChiefGuest());
        mListEventsHolder.tv_date_event.setText("" + mEventsModel.getmDateOfEvent());
        mListEventsHolder.tv_details.setText("" + mEventsModel.getmDetails());

        final ListEventsHolder finalMListEventsHolder = mListEventsHolder;

        setDataToPieChart(mListEventsHolder, mListEventsHolder.pie_events, 5, 3);
        mListEventsHolder.rl_more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEventsModel.isVisible()) {
                    finalMListEventsHolder.iv_up.setVisibility(View.GONE);
                    finalMListEventsHolder.iv_down.setVisibility(View.VISIBLE);
                    finalMListEventsHolder.ll_more_details.setVisibility(View.GONE);
                    mEventsModel.setVisible(false);
                    finalMListEventsHolder.tv_more_details.setText("More Details");
                } else {
                    finalMListEventsHolder.iv_up.setVisibility(View.VISIBLE);
                    finalMListEventsHolder.iv_down.setVisibility(View.GONE);
                    finalMListEventsHolder.ll_more_details.setVisibility(View.VISIBLE);
                    mEventsModel.setVisible(true);
                    finalMListEventsHolder.tv_more_details.setText("Less Details");
                }

            }
        });
        return convertView;
    }

    private void setDataToPieChart(ListEventsHolder mListEventsHolder, PieChart pie_events, int EntryOne, int EntryTwo) {

        ArrayList<PieEntry> entries_month = new ArrayList<>();
        entries_month.add(new PieEntry((float) EntryOne, 0));
        entries_month.add(new PieEntry((float) EntryTwo, 1));
        PieDataSet dataset_month = new PieDataSet(entries_month, "");
        int trasparent_theme = ColorUtils.compositeColors(mContext.getResources().getColor(R.color.themeColor), 100);
        dataset_month.setColors(new int[]{trasparent_theme, mContext.getResources().getColor(R.color.light_orange), mContext.getResources().getColor(R.color.themeColor)});
        PieData data_month = new PieData(dataset_month);

        mListEventsHolder.pie_events.setDescription("");
        mListEventsHolder.pie_events.setData(data_month);
        mListEventsHolder.pie_events.animateY(1000);
        mListEventsHolder.pie_events.setDrawSliceText(false);
        mListEventsHolder.pie_events.setUsePercentValues(false);
        mListEventsHolder.pie_events.setDrawSliceText(false);
        mListEventsHolder.pie_events.getData().setDrawValues(false);
        mListEventsHolder.pie_events.setHighlightPerTapEnabled(false);
//        pie_chart_right.saveToGallery("/sd/mychart.jpg", 85);
        pie_events.setHoleRadius(75f);
        pie_events.setHighlightPerTapEnabled(false);
        // pie_chart_right.setAlpha(0.4f);
        //for remove square color box
        Legend legend_right = mListEventsHolder.pie_events.getLegend();
        legend_right.setEnabled(false);
    }


    private class ListEventsHolder {

        private ImageView iv_down;
        private ImageView iv_up;
        private TextView tv_more_details;
        private LinearLayout ll_more_details;
        private RelativeLayout rl_more_details;
        private PieChart pie_events;

        private TextView tv_venu;
        private TextView tv_organized_by;
        private TextView tv_contact;
        private TextView tv_chief_guest;
        private TextView tv_date_event;
        private TextView tv_details;
    }
}
