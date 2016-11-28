package com.versatilemobitech.fmc.fragments;

import android.content.Intent;
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
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.LoginParser;
import com.versatilemobitech.fmc.parsers.PhotoAlbumsParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class GalleryFragment extends Fragment implements IAsyncCaller {
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
        /*grid_view.setOnItemClickListener(this);*/

        getGalleryFromApi("1");
    }

    public void getGalleryFromApi(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        PhotoAlbumsParser mPhotoAlbumsParser = new PhotoAlbumsParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.PHOTO_ALBUMS + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mPhotoAlbumsParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    mParent,
                    mParent.getResources().getString(
                            R.string.no_internet_msg),
                    mParent.getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }
   /* @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", 0);
        Utility.navigateDashBoardFragment(new GalleryViewFragment(), GalleryViewFragment.TAG, bundle, mParent);
    }*/

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof GalleryFolderModel) {
                    GalleryFolderModel mGalleryFolderModel = (GalleryFolderModel) model;

                    galleryFolderAdapter = new GalleryFolderAdapter(getActivity(), mGalleryFolderModel.getmList());
                    grid_view.setAdapter(galleryFolderAdapter);
                }
            }
        }
    }

}