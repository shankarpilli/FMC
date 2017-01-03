package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.utility.Utility;

/**
 * Created by Shankar on 5/14/2016.
 */
public class NoPostFoundAdapter extends BaseAdapter {
    private View view = null;
    private PrivateHolder holder;
    private LayoutInflater mInflater;
    private HomeActivity dashBoardActivity;

    public NoPostFoundAdapter(HomeActivity dashBoardActivity) {
        this.dashBoardActivity = dashBoardActivity;
        mInflater = (LayoutInflater) dashBoardActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        if (convertView == null) {
            holder = new PrivateHolder();
            view = mInflater.inflate(R.layout.noposts_found, null, false);
            holder.mTxtNoPosts = (TextView) view.findViewById(R.id.mTxtNoPosts);
            view.setTag(holder);
        } else {
            holder = (PrivateHolder) view.getTag();
        }
        Utility.showLog("Called Private", "Private");
        holder.mTxtNoPosts.setTypeface(Utility.setTypeFaceRobotoRegular(dashBoardActivity));

        return view;
    }

    private class PrivateHolder {
        private TextView mTxtNoPosts;
    }
}
