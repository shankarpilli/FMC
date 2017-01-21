package com.versatilemobitech.fmc.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.graphics.ColorUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.EventResponseModel;
import com.versatilemobitech.fmc.models.EventsModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.EventsResponseParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Rev's Nani on 06-11-2016.
 */

public class EventsAdapter extends BaseAdapter implements IAsyncCaller {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<EventsModel> mEventsModelList;
    private EventsModel mEventsModel;


    private ImageView iv_up_new_item;
    private ImageView iv_down_new_item;
    private LinearLayout ll_more_details_new_item;
    private TextView tv_more_details_new_item;
    private int mClickedPosition = -1;

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
        mListEventsHolder.ll_more_details = (LinearLayout) convertView.findViewById(R.id.ll_more_details);
        mListEventsHolder.rl_more_details = (RelativeLayout) convertView.findViewById(R.id.rl_more_details);
        mListEventsHolder.pie_events = (PieChart) convertView.findViewById(R.id.pie_events);

        mListEventsHolder.tv_more_details = (TextView) convertView.findViewById(R.id.tv_more_details);
        mListEventsHolder.tv_more_details.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
        mListEventsHolder.tv_location.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_event_title = (TextView) convertView.findViewById(R.id.tv_event_title);
        mListEventsHolder.tv_event_title.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_venu = (TextView) convertView.findViewById(R.id.tv_venu);
        mListEventsHolder.tv_venu.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_organized_by = (TextView) convertView.findViewById(R.id.tv_organized_by);
        mListEventsHolder.tv_organized_by.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_contact = (TextView) convertView.findViewById(R.id.tv_contact);
        mListEventsHolder.tv_contact.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_chief_guest = (TextView) convertView.findViewById(R.id.tv_chief_guest);
        mListEventsHolder.tv_chief_guest.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_date_event = (TextView) convertView.findViewById(R.id.tv_date_event);
        mListEventsHolder.tv_date_event.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_details = (TextView) convertView.findViewById(R.id.tv_details);
        mListEventsHolder.tv_details.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_lable_one = (TextView) convertView.findViewById(R.id.tv_lable_one);
        mListEventsHolder.tv_lable_one.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_lable_two = (TextView) convertView.findViewById(R.id.tv_lable_one);
        mListEventsHolder.tv_lable_two.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_lable_three = (TextView) convertView.findViewById(R.id.tv_lable_three);
        mListEventsHolder.tv_lable_three.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_lable_four = (TextView) convertView.findViewById(R.id.tv_lable_four);
        mListEventsHolder.tv_lable_four.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_lable_five = (TextView) convertView.findViewById(R.id.tv_lable_five);
        mListEventsHolder.tv_lable_five.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_lable_six = (TextView) convertView.findViewById(R.id.tv_lable_six);
        mListEventsHolder.tv_lable_six.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_venu_lable = (TextView) convertView.findViewById(R.id.tv_venu_lable);
        mListEventsHolder.tv_venu_lable.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_details_lable = (TextView) convertView.findViewById(R.id.tv_details_lable);
        mListEventsHolder.tv_details_lable.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_organized_by_label = (TextView) convertView.findViewById(R.id.tv_organized_by_label);
        mListEventsHolder.tv_organized_by_label.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_contact_lable = (TextView) convertView.findViewById(R.id.tv_contact_lable);
        mListEventsHolder.tv_contact_lable.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_chief_guest_lable = (TextView) convertView.findViewById(R.id.tv_chief_guest_lable);
        mListEventsHolder.tv_chief_guest_lable.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_date_event_label = (TextView) convertView.findViewById(R.id.tv_date_event_label);
        mListEventsHolder.tv_date_event_label.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_accept = (TextView) convertView.findViewById(R.id.tv_accept);
        mListEventsHolder.tv_accept.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_decline = (TextView) convertView.findViewById(R.id.tv_decline);
        mListEventsHolder.tv_decline.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        mListEventsHolder.tv_balance_days = (TextView) convertView.findViewById(R.id.tv_balance_days);
        mListEventsHolder.tv_balance_days.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

    /*    mListEventsHolder.tv_contact.setText("" + mEventsModel.getmContact());
        mListEventsHolder.tv_chief_guest.setText("" + mEventsModel.getmChiefGuest());
        mListEventsHolder.tv_date_event.setText("" + mEventsModel.getmDateOfEvent());
        mListEventsHolder.tv_details.setText("" + mEventsModel.getmDetails());*/


        final ListEventsHolder finalMListEventsHolder = mListEventsHolder;

        setDataToPieChart(mListEventsHolder, mListEventsHolder.pie_events, 5, 3);

        mListEventsHolder.rl_more_details.setTag(position);
        mListEventsHolder.rl_more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = (int) v.getTag();
                if (mEventsModelList.get(selectedPosition).isVisible()) {
                    finalMListEventsHolder.iv_up.setVisibility(View.GONE);
                    finalMListEventsHolder.iv_down.setVisibility(View.VISIBLE);
                    finalMListEventsHolder.ll_more_details.setVisibility(View.GONE);
                    //mEventsModel.setVisible(false);
                    mEventsModelList.get(selectedPosition).setVisible(false);
                    finalMListEventsHolder.tv_more_details.setText("More Details");

                    iv_up_new_item.setVisibility(View.GONE);
                    iv_down_new_item.setVisibility(View.VISIBLE);
                    ll_more_details_new_item.setVisibility(View.GONE);
                    tv_more_details_new_item.setVisibility(View.VISIBLE);
                    if (mClickedPosition != -1) {
                        mEventsModelList.get(mClickedPosition).setVisible(false);
                    }

                } else {
                    if (iv_up_new_item != null) {
                        iv_up_new_item.setVisibility(View.GONE);
                        iv_down_new_item.setVisibility(View.VISIBLE);
                        ll_more_details_new_item.setVisibility(View.GONE);
                        tv_more_details_new_item.setVisibility(View.VISIBLE);
                        tv_more_details_new_item.setText("More Details");
                        if (mClickedPosition != -1) {
                            mEventsModelList.get(mClickedPosition).setVisible(false);
                        }
                    }

                    finalMListEventsHolder.iv_up.setVisibility(View.VISIBLE);
                    finalMListEventsHolder.iv_down.setVisibility(View.GONE);
                    finalMListEventsHolder.ll_more_details.setVisibility(View.VISIBLE);
                    //mEventsModel.setVisible(true);
                    mEventsModelList.get(selectedPosition).setVisible(true);
                    finalMListEventsHolder.tv_more_details.setText("Less Details");

                    mClickedPosition = selectedPosition;
                    iv_up_new_item = finalMListEventsHolder.iv_up;
                    iv_down_new_item = finalMListEventsHolder.iv_down;
                    ll_more_details_new_item = finalMListEventsHolder.ll_more_details;
                    tv_more_details_new_item = finalMListEventsHolder.tv_more_details;
                }

            }
        });
        mListEventsHolder.tv_accept.setTag(position);
        mListEventsHolder.tv_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedPosition = (int) view.getTag();
                showConformationDialog("Accept", mEventsModelList.get(selectedPosition).getEvent_id());
            }
        });

        mListEventsHolder.tv_decline.setTag(position);
        mListEventsHolder.tv_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedPosition = (int) view.getTag();
                showConformationDialog("Decline", mEventsModelList.get(selectedPosition).getEvent_id());
            }
        });

        setData(mListEventsHolder);
        return convertView;
    }

    private void showConformationDialog(final String response, final String event_id) {
        final Dialog dialogEventConfirmation = new Dialog(mContext);
        dialogEventConfirmation.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogEventConfirmation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEventConfirmation.setContentView(R.layout.dialog_events_confromation);
        //dialogEventConfirmation.getWindow().setGravity(Gravity.BOTTOM);
        dialogEventConfirmation.setCanceledOnTouchOutside(true);
        //dialogEventConfirmation.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialogEventConfirmation.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView txt_dialog_message = (TextView) dialogEventConfirmation.findViewById(R.id.txt_dialog_message);
        TextView tv_yes = (TextView) dialogEventConfirmation.findViewById(R.id.tv_yes);
        TextView tv_no = (TextView) dialogEventConfirmation.findViewById(R.id.tv_no);

        if (response.equalsIgnoreCase("Accept")) {
            txt_dialog_message.setText("" + Utility.getResourcesString(mContext, R.string.are_you_sure_you_want_to_attend_the_event));
        } else {
            txt_dialog_message.setText("" + Utility.getResourcesString(mContext, R.string.are_you_sure_you_want_to_decline_the_event));
        }
        txt_dialog_message.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
        tv_yes.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));
        tv_no.setTypeface(Utility.setTypeFaceRobotoRegular(mContext));

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postYourResponseOnWeb(response, event_id);
                dialogEventConfirmation.dismiss();
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEventConfirmation.dismiss();
            }
        });

        dialogEventConfirmation.show();
    }

    private void postYourResponseOnWeb(String response, String event_id) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("user_id", Utility.getSharedPrefStringData(mContext, Constants.USER_ID));
        paramMap.put("event_id", event_id);
        paramMap.put("response_text", response);

        EventsResponseParser mEventsResponseParser = new EventsResponseParser();
        if (Utility.isNetworkAvailable(mContext)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mContext, Utility.getResourcesString(mContext,
                    R.string.please_wait), true,
                    APIConstants.EVENT_RESPONSE, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mEventsResponseParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    mContext,
                    mContext.getResources().getString(
                            R.string.no_internet_msg),
                    mContext.getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }

    private void setData(ListEventsHolder mListEventsHolder) {
        mListEventsHolder.tv_event_title.setText(mEventsModel.getEvent_title());

        mListEventsHolder.tv_venu.setText(mEventsModel.getVenue());
        mListEventsHolder.tv_organized_by.setText(mEventsModel.getOrganized_by());
        mListEventsHolder.tv_contact.setText(mEventsModel.getContact());
        mListEventsHolder.tv_chief_guest.setText(mEventsModel.getChief_guest());
        mListEventsHolder.tv_date_event.setText(mEventsModel.getDate_of_event());
        mListEventsHolder.tv_details.setText(mEventsModel.getDetails());
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

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof EventResponseModel) {
                    EventResponseModel mEventResponseModel = (EventResponseModel) model;
                    Utility.showToastMessage(mContext, mEventResponseModel.getMessage());
                }
            }
        }
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

        private TextView tv_lable_one;
        private TextView tv_lable_two;
        private TextView tv_lable_three;
        private TextView tv_lable_four;
        private TextView tv_lable_five;
        private TextView tv_lable_six;

        private TextView tv_date_event_label;
        private TextView tv_venu_lable;
        private TextView tv_organized_by_label;
        private TextView tv_contact_lable;
        private TextView tv_chief_guest_lable;
        private TextView tv_details_lable;

        private TextView tv_accept;
        private TextView tv_decline;
        private TextView tv_balance_days;
        private TextView tv_event_title;

        private TextView tv_location;
    }
}
