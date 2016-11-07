package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.GalleryFolderAdapter;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = "GalleryFragment";
    private DashboardActivity mParent;
    private View rootView;

    private GridView grid_view;
    private GalleryFolderAdapter galleryFolderAdapter;
    private ArrayList<GalleryFolderModel> galleryFolderModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.gallery));
        rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        grid_view = (GridView) rootView.findViewById(R.id.grid_view);
        galleryFolderModels = new ArrayList<>();
        galleryFolderAdapter = new GalleryFolderAdapter(getActivity(), galleryFolderModels);
        grid_view.setAdapter(galleryFolderAdapter);
        grid_view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", 0);
        Utility.navigateDashBoardFragment(new GalleryViewFragment(), GalleryViewFragment.TAG, bundle, mParent);
    }
}