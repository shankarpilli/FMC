package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Shankar on 12/21/2016.
 */

public class FileChooseAdapter extends BaseAdapter {

    private ArrayList<File> files;
    private String mFor;
    private Context mParent;
    private LayoutInflater mLayoutInflater;

    public FileChooseAdapter(Context mParent, ArrayList<File> files, String mFor) {
        this.mParent = mParent;
        this.files = files;
        mLayoutInflater = (LayoutInflater) mParent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public File getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FileChooseAdapter.FileChooseHolder mFileChooseHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.file_choose_item,
                    null);
            mFileChooseHolder = new FileChooseAdapter.FileChooseHolder();
            mFileChooseHolder.txt_left_drawer_text = (TextView) convertView.findViewById(R.id.txt_left_drawer_text);
            mFileChooseHolder.img_left_drawer_icon = (ImageView) convertView.findViewById(R.id.img_left_drawer_icon);
            mFileChooseHolder.txt_left_drawer_text.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
            convertView.setTag(mFileChooseHolder);
        } else {
            mFileChooseHolder = (FileChooseAdapter.FileChooseHolder) convertView.getTag();
        }

        File file = (File) getItem(position);
        mFileChooseHolder.txt_left_drawer_text.setText("SS" + file.getName());

        return convertView;
    }

    private class FileChooseHolder {
        private TextView txt_left_drawer_text;
        private ImageView img_left_drawer_icon;
    }
}
