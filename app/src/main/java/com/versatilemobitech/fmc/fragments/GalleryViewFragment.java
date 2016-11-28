package com.versatilemobitech.fmc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.GalleryFolderAdapter;
import com.versatilemobitech.fmc.adapters.GalleryViewAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.GalleryViewModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.GalleryViewParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class GalleryViewFragment extends Fragment implements IAsyncCaller {
    public static final String TAG = "GalleryFragment";
    private DashboardActivity mParent;
    private View rootView;

    private GridView grid_view;
    private GalleryViewAdapter galleryViewAdapter;
    private ArrayList<GalleryViewModel> galleryViewModels;

    private String albumId;
    private String albumName;
    private int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();

        Bundle mBundle = getArguments();
        albumId = mBundle.getString("albumId");
        albumName = mBundle.getString("albumName");
        mPosition = mBundle.getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(albumName);
        rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        grid_view = (GridView) rootView.findViewById(R.id.grid_view);
        getGalleryFromApi("1", "1");
       /* galleryViewModels = new ArrayList<>();
        galleryViewAdapter = new GalleryViewAdapter(getActivity(), galleryViewModels);
        grid_view.setAdapter(galleryViewAdapter);*/
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof GalleryViewModel) {
                    GalleryViewModel mGalleryViewModel = (GalleryViewModel) model;

                    galleryViewModels = mGalleryViewModel.getmList();
                    galleryViewAdapter = new GalleryViewAdapter(getActivity(), galleryViewModels);
                    grid_view.setAdapter(galleryViewAdapter);
                }
            }
        }
    }

    public void getGalleryFromApi(String mPageNumber, String mAlbumId) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();

        GalleryViewParser mGalleryViewParser = new GalleryViewParser();
        if (Utility.isNetworkAvailable(mParent)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.PHOTO_GALLERY + mAlbumId + "/" + mPageNumber, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mGalleryViewParser);
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
}