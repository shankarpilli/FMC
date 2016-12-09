package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.models.MembersModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/25/2016.
 */

public class MembersAdapter extends BaseAdapter implements Filterable {

    private ArrayList<MembersModel> _MembersModel;
    private Context context;
    private LayoutInflater inflater;
    private ValueFilter valueFilter;
    private ArrayList<MembersModel> membersModels;

    public MembersAdapter(Context context, ArrayList<MembersModel> _MembersModel) {
        super();
        this.context = context;
        this._MembersModel = _MembersModel;
        membersModels = _MembersModel;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getFilter();
    }

    @Override
    public int getCount() {
        return _MembersModel.size();
    }

    @Override
    public MembersModel getItem(int position) {
        return membersModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class MembersItemHolder {
        private TextView txt_your_name;
        private TextView txt_company;
        private ImageView img_member;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MembersItemHolder holder;
        if (convertView == null) {
            holder = new MembersItemHolder();
            convertView = inflater.inflate(R.layout.member_search_item, null);
            holder.txt_your_name = (TextView) convertView.findViewById(R.id.txt_your_name);
            holder.txt_company = (TextView) convertView.findViewById(R.id.txt_company);
            holder.img_member = (ImageView) convertView.findViewById(R.id.img_member);
            holder.txt_your_name.setTypeface(Utility.setTypeFaceRobotoRegular(context));
            holder.txt_company.setTypeface(Utility.setTypeFaceRobotoRegular(context));
            convertView.setTag(holder);
        } else
            holder = (MembersItemHolder) convertView.getTag();

        holder.txt_your_name.setText("" + Utility.capitalizeFirstLetter(_MembersModel.get(position).getmName()) + " " +
                Utility.capitalizeFirstLetter(_MembersModel.get(position).getLast_name()));
        holder.txt_company.setText("" + Utility.capitalizeFirstLetter(_MembersModel.get(position).getmCompany()));

        if (!Utility.isValueNullOrEmpty(_MembersModel.get(position).getmImage()))
            Picasso.with(context)
                    .load(_MembersModel.get(position).getmImage()).transform(new CircleTransform())
                    .placeholder(Utility.getDrawable(context, R.drawable.folder_icon))
                    .into(holder.img_member);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {

        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<MembersModel> filterList = new ArrayList<MembersModel>();
                for (int i = 0; i < membersModels.size(); i++) {
                    if ((membersModels.get(i).getmName().toUpperCase() + " " + membersModels.get(i).getLast_name().toUpperCase()
                            + " " + membersModels.get(i).getmCompany().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        MembersModel mMembersModel = new MembersModel();
                        mMembersModel.setmName(membersModels.get(i).getmName());
                        mMembersModel.setUser_id(membersModels.get(i).getUser_id());
                        mMembersModel.setmCompany(membersModels.get(i).getmCompany());
                        mMembersModel.setLast_name(membersModels.get(i).getLast_name());
                        mMembersModel.setmImage(membersModels.get(i).getmImage());
                        filterList.add(mMembersModel);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = membersModels.size();
                results.values = membersModels;
            }
            return results;
        }


        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            _MembersModel = (ArrayList<MembersModel>) results.values;
            notifyDataSetChanged();
        }
    }
}

