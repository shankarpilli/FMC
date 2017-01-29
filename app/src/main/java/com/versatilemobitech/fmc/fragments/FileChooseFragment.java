package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.FileChooseAdapter;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Shankar on 12/21/2016.
 */

public class FileChooseFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = "FileChooseFragment";
    private HomeActivity mParent;
    private View rootView;

    private ListView list_files;
    private TextView txt_no_files_found;
    private TextView txt_choose_file;

    private String mSelected = "";
    private FileChooseAdapter fileChooseAdapter;
    private ArrayList<File> fileList = new ArrayList<File>();
    private ArrayList<File> fileArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        mSelected = getArguments().getString("file");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent,
                Utility.getResourcesString(getActivity(), R.string.home)));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_file_layout, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        list_files = (ListView) rootView.findViewById(R.id.list_files);
        txt_no_files_found = (TextView) rootView.findViewById(R.id.txt_no_files_found);
        txt_choose_file = (TextView) rootView.findViewById(R.id.txt_choose_file);
        txt_no_files_found.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        txt_choose_file.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        txt_no_files_found.setVisibility(View.GONE);

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (mSelected.equalsIgnoreCase(".pdf")) {
            txt_choose_file.setText("Choose Pdf File");
            txt_no_files_found.setText("No Pdf Found");
            fileArrayList = search_Dir(dir);
        } else {
            txt_choose_file.setText("Choose Doc File");
            txt_no_files_found.setText("No Doc Found");
            fileArrayList = search_DocDir(dir);
        }

        if (fileArrayList != null && fileArrayList.size() > 0) {
            fileChooseAdapter = new FileChooseAdapter(mParent, fileArrayList, mSelected);
            list_files.setAdapter(fileChooseAdapter);
        } else {
            list_files.setVisibility(View.GONE);
            txt_no_files_found.setVisibility(View.VISIBLE);
        }
        list_files.setOnItemClickListener(this);
    }

    public ArrayList<File> search_DocDir(File dir) {
        File FileList[] = dir.listFiles();
        if (FileList != null) {
            for (int i = 0; i < FileList.length; i++) {

                if (FileList[i].isDirectory()) {
                    search_DocDir(FileList[i]);
                } else {
                    if (FileList[i].getName().endsWith(mSelected) || FileList[i].getName().endsWith(".docx")) {
                        fileList.add(FileList[i]);
                    }
                }
            }
        }
        return fileList;
    }

    public ArrayList<File> search_Dir(File dir) {
        File FileList[] = dir.listFiles();
        if (FileList != null) {
            for (int i = 0; i < FileList.length; i++) {

                if (FileList[i].isDirectory()) {
                    search_Dir(FileList[i]);
                } else {
                    if (FileList[i].getName().endsWith(mSelected)) {
                        fileList.add(FileList[i]);
                    }
                }
            }
        }
        return fileList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mSelected.equalsIgnoreCase(".pdf")) {
            HomeFragment.getInstance().updatePdf(fileArrayList.get(i));
            mParent.onBackPressed();
        } else {
            HomeFragment.getInstance().updateDoc(fileArrayList.get(i));
            mParent.onBackPressed();
        }
    }
}