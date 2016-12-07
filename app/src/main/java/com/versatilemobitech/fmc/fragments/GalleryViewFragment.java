package com.versatilemobitech.fmc.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.DashboardActivity;
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
public class GalleryViewFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    public static final String TAG = "GalleryFragment";
    private DashboardActivity mParent;
    private View rootView;

    private GridView grid_view;
    private TextView tv_no_images;
    private GalleryViewAdapter galleryViewAdapter;
    private ArrayList<GalleryViewModel> galleryViewModels;

    private String albumId;
    private String albumName;
    private int mPosition;

    private int aaTotalCount, aaVisibleCount, aaFirstVisibleItem;
    private boolean endScroll = false;
    private int mPageNumber = 1;

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
        tv_no_images = (TextView) rootView.findViewById(R.id.tv_no_images);
        getGalleryFromApi(albumId, "1");
       /* galleryViewModels = new ArrayList<>();
        galleryViewAdapter = new GalleryViewAdapter(getActivity(), galleryViewModels);
        grid_view.setAdapter(galleryViewAdapter);*/
        grid_view.setOnScrollListener(this);
        grid_view.setOnItemClickListener(this);
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof GalleryViewModel) {
                    GalleryViewModel mGalleryViewModel = (GalleryViewModel) model;
                    if (galleryViewModels == null) {
                        if (mGalleryViewModel.getmList() == null) {
                            tv_no_images.setVisibility(View.VISIBLE);
                            grid_view.setVisibility(View.GONE);
                        } else {
                            tv_no_images.setVisibility(View.GONE);
                            grid_view.setVisibility(View.VISIBLE);
                            if (galleryViewModels == null) {
                                galleryViewModels = new ArrayList<GalleryViewModel>();
                            }
                            galleryViewModels.addAll(mGalleryViewModel.getmList());
                            if (galleryViewAdapter == null) {
                                setGridData();
                            }
                        }
                    } else {
                        grid_view.setVisibility(View.VISIBLE);
                        tv_no_images.setVisibility(View.GONE);
                        if (mGalleryViewModel.getmList() != null && mGalleryViewModel.getmList().size() > 0) {
                            galleryViewModels.addAll(mGalleryViewModel.getmList());
                            if (galleryViewAdapter == null) {
                                setGridData();
                            } else {
                                galleryViewAdapter.notifyDataSetChanged();
                            }
                        } else {
                            endScroll = true;
                        }
                    }
                }
            }
        }
    }


    /*Image full view*/
    private void showFitDialog(String url, Context context) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_fitcenter);
        dialog.setCanceledOnTouchOutside(false);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.image);
        Picasso.with(getActivity())
                .load(url)
                .placeholder(Utility.getDrawable(getActivity(), R.drawable.folder_icon))
                .into(imageView);
        dialog.show();
    }

    private void setGridData() {
        galleryViewAdapter = new GalleryViewAdapter(getActivity(), galleryViewModels);
        grid_view.setAdapter(galleryViewAdapter);
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
                getGalleryFromApi("" + mPageNumber, albumId);
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
        showFitDialog(galleryViewModels.get(position).getImage_path(), getActivity());
    }
}