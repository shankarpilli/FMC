package com.versatilemobitech.fmc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.GalleryFolderAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.GalleryFolderModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.PhotoAlbumsParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/07/2016
 */
public class GalleryFragment extends Fragment implements IAsyncCaller, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    public static final String TAG = "GalleryFragment";
    private HomeActivity mParent;
    private View rootView;

    private GridView grid_view;
    private TextView tv_no_images;
    private GalleryFolderAdapter galleryFolderAdapter;
    private ArrayList<GalleryFolderModel> galleryFolderModels;

    private int aaTotalCount, aaVisibleCount, aaFirstVisibleItem;
    private boolean endScroll = false;
    private int mPageNumber = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent,
                Utility.getResourcesString(getActivity(), R.string.gallery)));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
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
        grid_view.setOnScrollListener(this);
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
                    GalleryFolderModel mGalleryFolderModel = (GalleryFolderModel) model;
                    if (galleryFolderModels == null) {
                        if (mGalleryFolderModel.getmList() == null) {
                            tv_no_images.setVisibility(View.VISIBLE);
                            grid_view.setVisibility(View.GONE);
                        } else {
                            tv_no_images.setVisibility(View.GONE);
                            grid_view.setVisibility(View.VISIBLE);
                            if (galleryFolderModels == null) {
                                galleryFolderModels = new ArrayList<GalleryFolderModel>();
                            }
                            galleryFolderModels.addAll(mGalleryFolderModel.getmList());
                            if (galleryFolderAdapter == null) {
                                setGridData();
                            }
                        }
                    } else {
                        grid_view.setVisibility(View.VISIBLE);
                        tv_no_images.setVisibility(View.GONE);
                        if (mGalleryFolderModel.getmList() != null && mGalleryFolderModel.getmList().size() > 0) {
                            galleryFolderModels.addAll(mGalleryFolderModel.getmList());
                            if (galleryFolderAdapter == null) {
                                setGridData();
                            } else {
                                galleryFolderAdapter.notifyDataSetChanged();
                            }
                        } else {
                            endScroll = true;
                        }
                    }
                }
            }
        }
    }

    private void setGridData() {
        galleryFolderAdapter = new GalleryFolderAdapter(getActivity(), galleryFolderModels);
        grid_view.setAdapter(galleryFolderAdapter);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            isScrollCompleted();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        aaTotalCount = totalItemCount;
        aaVisibleCount = visibleItemCount;
        aaFirstVisibleItem = firstVisibleItem;
    }

    private void isScrollCompleted() {
        if (aaTotalCount == (aaFirstVisibleItem + aaVisibleCount) && !endScroll) {
            if (Utility.isNetworkAvailable(getActivity())) {
                mPageNumber = mPageNumber + 1;
                getGalleryFromApi("" + mPageNumber);
                Utility.showLog("mPageNumber", "mPageNumber : " + mPageNumber);
            } else {
                Utility.showSettingDialog(
                        getActivity(),
                        getActivity().getResources().getString(
                                R.string.no_internet_msg),
                        getActivity().getResources().getString(
                                R.string.no_internet_title),
                        Utility.NO_INTERNET_CONNECTION).show();
            }
        } else {
            if (grid_view.getAdapter() != null) {
                if (grid_view.getLastVisiblePosition() == grid_view.getAdapter().getCount() - 1 &&
                        grid_view.getChildAt(grid_view.getChildCount() - 1).getBottom() <= grid_view.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        GalleryFolderModel mLeftMenuModel = galleryFolderModels.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("albumId", mLeftMenuModel.getPhoto_album_id());
        bundle.putString("albumName", mLeftMenuModel.getAlbum_name());
        Utility.navigateDashBoardFragment(new GalleryViewFragment(), GalleryViewFragment.TAG, bundle, mParent);
    }
}