package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
import com.versatilemobitech.fmc.adapters.GalleryFolderAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.PhotoAlbumsParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class GalleryFragment extends Fragment implements IAsyncCaller, AdapterView.OnItemClickListener {
    public static final String TAG = "GalleryFragment";
    private DashboardActivity mParent;
    private View rootView;

    private GridView grid_view;
    private TextView tv_no_images;
    private GalleryFolderAdapter galleryFolderAdapter;
    private GalleryFolderModel mGalleryFolderModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.txt_fmc.setText(Utility.getResourcesString(getActivity(), R.string.gallery));
        if (rootView != null) {
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        grid_view = (GridView) rootView.findViewById(R.id.grid_view);
        tv_no_images = (TextView) rootView.findViewById(R.id.tv_no_images);
        /*grid_view.setOnItemClickListener(this);*/

        getGalleryFromApi("1");
        grid_view.setOnItemClickListener(this);
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
                    mGalleryFolderModel = (GalleryFolderModel) model;
                    if (mGalleryFolderModel.getmList() != null && mGalleryFolderModel.getmList().size() != 0) {
                        tv_no_images.setVisibility(View.GONE);
                        galleryFolderAdapter = new GalleryFolderAdapter(getActivity(), mGalleryFolderModel.getmList());
                        grid_view.setAdapter(galleryFolderAdapter);
                    } else {
                        tv_no_images.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        GalleryFolderModel mLeftMenuModel = mGalleryFolderModel.getmList().get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("albumId", mLeftMenuModel.getPhoto_album_id());
        bundle.putString("albumName", mLeftMenuModel.getAlbum_name());
        Utility.navigateDashBoardFragment(new GalleryViewFragment(), GalleryViewFragment.TAG, bundle, mParent);
    }
}