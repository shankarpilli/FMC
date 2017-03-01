package com.versatilemobitech.fmc.fragments;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.adapters.HomeAdapter;
import com.versatilemobitech.fmc.adapters.NoPostFoundAdapter;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.customviews.RoundedCornersTransformation;
import com.versatilemobitech.fmc.designs.MaterialDialog;
import com.versatilemobitech.fmc.interfaces.IUpdateSelectedPic;
import com.versatilemobitech.fmc.models.GetPostsModel;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.PostDataModel;
import com.versatilemobitech.fmc.parsers.CommentsParser;
import com.versatilemobitech.fmc.parsers.GetPostsParser;
import com.versatilemobitech.fmc.parsers.PostDataParser;
import com.versatilemobitech.fmc.permissions.Permissions;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli on 11/06/2016
 */
public class HomeFragment extends Fragment implements IAsyncCaller, AbsListView.OnScrollListener,
        IUpdateSelectedPic, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "HomeFragment";
    private HomeActivity mParent;
    private View rootView;

    private ListView list_view;
    private TextView tv_no_posts;
    private HomeAdapter homeAdapter;
    private NoPostFoundAdapter noPostFoundAdapter;
    private ArrayList<HomeDataModel> homeDataModels;

    private boolean isDocSelected;
    private boolean isPdfSelected;
    private boolean isImgSelected;

    private String mImgpath;
    private String mImgpathExtenstion;
    private String mDoc_extension;
    private String mEncodedImage;
    private String mEncodedDoc;

    private String mPdfpath;
    private String mDocpath;

    private TextView txt_path;
    private TextView txt_close;
    private LinearLayout layout_list_header;
    private LinearLayout ll_file_layout;
    private EditText et_what_is_on_u_mind;

    private int aaTotalCount, aaVisibleCount, aaFirstVisibleItem;
    private boolean endScroll = false;
    private int mPageNumber = 1;

    private int mCommtedPostion = -1;
    private String mCommtedMessage = "";

    private ImageView fab;
    private Dialog dialogCompleted;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String postID = "";

    private static IUpdateSelectedPic iUpdateSelectedPic;

    public static IUpdateSelectedPic getInstance() {
        return iUpdateSelectedPic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        iUpdateSelectedPic = this;
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(Constants.POST_ID)) {
            postID = bundle.getString(Constants.POST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent, Utility.getResourcesString(getActivity(), R.string.home)));
        if (rootView != null) {
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        list_view = (ListView) rootView.findViewById(R.id.list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        fab = (ImageView) rootView.findViewById(R.id.fab);
        tv_no_posts = (TextView) rootView.findViewById(R.id.tv_no_posts);
        noPostFoundAdapter = new NoPostFoundAdapter(mParent);
        /*homeAdapter = new HomeAdapter(getActivity(), homeDataModels);
        list_view.setAdapter(homeAdapter);*/

        getHomeFeeds("1");
        list_view.setOnScrollListener(this);
        fab.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (Utility.isMarshmallowOS()) {
            Permissions.getInstance().setActivity(getActivity());
            CheckForPermissions(getActivity(), Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!Utility.isValueNullOrEmpty(postID)) {
            Bundle bundle = new Bundle();
            bundle.putString("post_id", postID);
            Utility.navigateDashBoardFragment(new DetailViewFragment(), DetailViewFragment.TAG, bundle, mParent);
        }

    }

    @Override
    public void onRefresh() {
        homeDataModels = null;
        homeAdapter = null;
        list_view.setAdapter(null);
        endScroll = false;
        mPageNumber = 1;
        getHomeFeeds("1");
    }

    private void CheckForPermissions(final Context mContext, final String... mPermisons) {
        // A request for two permissions
        Permissions.getInstance().requestPermissions(new Permissions.IOnPermissionResult() {
            @Override
            public void onPermissionResult(Permissions.ResultSet resultSet) {

                if (resultSet.isPermissionGranted(Manifest.permission.CAMERA) &&
                        resultSet.isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                        resultSet.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                } else {
                    final MaterialDialog denyDialog = new MaterialDialog(mContext, Permissions.TITLE,
                            Permissions.MESSAGE);
                    //Positive
                    denyDialog.setAcceptButton("RE-TRY");
                    denyDialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CheckForPermissions(mContext, mPermisons);
                        }
                    });
                    denyDialog.show();
                }
            }

            @Override
            public void onRationaleRequested(Permissions.IOnRationaleProvided callback, String... permissions) {
                Permissions.getInstance().showRationaleInDialog(Permissions.TITLE,
                        Permissions.MESSAGE, "RE-TRY", callback);
            }
        }, mPermisons);
    }

    private void getHomeFeeds(String mPageNumber) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        GetPostsParser mGetPostsParser = new GetPostsParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.GET_POSTS + mPageNumber + "/" + Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID), paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mGetPostsParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    getActivity(),
                    getActivity().getResources().getString(
                            R.string.no_internet_msg),
                    getActivity().getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }


    private void postFeed(EditText et_what_is_on_u_mind) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("post_text", "" + et_what_is_on_u_mind.getText().toString());
        paramMap.put("user_id", Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID));
        if (!Utility.isValueNullOrEmpty(mImgpathExtenstion)) {
            paramMap.put("post_image", mEncodedImage);
            paramMap.put("image_extension", mImgpathExtenstion);
        }
        if (!Utility.isValueNullOrEmpty(mDoc_extension)) {
            paramMap.put("doc_extension", mDoc_extension);
            paramMap.put("post_doc", mEncodedDoc);
        }
        PostDataParser mPostDataParser = new PostDataParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.SAVE_POST, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mPostDataParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    getActivity(),
                    getActivity().getResources().getString(
                            R.string.no_internet_msg),
                    getActivity().getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }

    private boolean isValidFields(EditText et_what_is_on_u_mind) {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_what_is_on_u_mind.getText().toString().trim()) && !isDocSelected && !isImgSelected && !isPdfSelected) {
            Utility.setSnackBarEnglish(mParent, et_what_is_on_u_mind, "Please enter what is on ur mind");
        } else {
            isValidated = true;
        }
        return isValidated;
    }


    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof GetPostsModel) {
                    if (swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                        //listStarFeeds.clear();
                    }
                    GetPostsModel mGetPostsModel = (GetPostsModel) model;
                    if (homeDataModels == null) {
                        if (mGetPostsModel.getmList() == null) {
                            /*tv_no_posts.setVisibility(View.VISIBLE);
                            list_view.setVisibility(View.GONE);*/
                            list_view.setAdapter(noPostFoundAdapter);
                            //setListHeader();
                        } else {
                            tv_no_posts.setVisibility(View.GONE);
                            list_view.setVisibility(View.VISIBLE);
                            if (homeDataModels == null) {
                                homeDataModels = new ArrayList<HomeDataModel>();
                            }
                            homeDataModels.addAll(mGetPostsModel.getmList());
                            if (homeAdapter == null) {
                                setListData();
                            }
                        }
                    } else {
                        list_view.setVisibility(View.VISIBLE);
                        tv_no_posts.setVisibility(View.GONE);
                        if (mGetPostsModel.getmList() != null && mGetPostsModel.getmList().size() > 0) {
                            homeDataModels.addAll(mGetPostsModel.getmList());
                            if (homeAdapter == null) {
                                setListData();
                            } else {
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            endScroll = true;
                        }
                    }
                } else if (model instanceof PostDataModel) {
                    PostDataModel postDataModel = (PostDataModel) model;
                    Utility.showToastMessage(mParent, postDataModel.getMessage());
                    //et_what_is_on_u_mind.setText("");
                    removeSelectedFileDialog();
                }
            }
        }
    }


    /*private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd MMM, YYYY HH:mm a", Locale.getDefault());
        Date date = new Date();
        String s = dateFormat.format(date);
        return s;
    }*/


    private void setListData() {
        homeAdapter = new HomeAdapter(mParent, getActivity(), this, homeDataModels);
        list_view.setAdapter(homeAdapter);
        //setListHeader();
    }

    public void commentOnPost(int position, String message) {
        mCommtedPostion = position;
        mCommtedMessage = message;
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("post_id", homeDataModels.get(position).getPost_id());
        paramMap.put("user_id", Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID));
        paramMap.put("comment", message);
        CommentsParser mCommentsParser = new CommentsParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.POST_COMMENT, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mCommentsParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    getActivity(),
                    getActivity().getResources().getString(
                            R.string.no_internet_msg),
                    getActivity().getResources().getString(
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
                getHomeFeeds("" + mPageNumber);
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
            if (list_view.getAdapter() != null) {
                if (list_view.getLastVisiblePosition() == list_view.getAdapter().getCount() - 1 &&
                        list_view.getChildAt(list_view.getChildCount() - 1).getBottom() <= list_view.getHeight()) {
                    Utility.showToastMessage(getActivity(), Utility.getResourcesString(getActivity(),
                            R.string.no_more_data_to_display));
                }
            }
        }
    }

    public void showSelectPhotoDialog() {
        final Dialog dialogShare = new Dialog(getActivity());
        dialogShare.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogShare.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogShare.setContentView(R.layout.dialog_choose_photo_popup);
        dialogShare.getWindow().setGravity(Gravity.BOTTOM);
        dialogShare.setCanceledOnTouchOutside(true);
        dialogShare.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialogShare.getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));


        TextView txt_shr_popup_title = (TextView) dialogShare.findViewById(R.id.txt_shr_popup_title);
        txt_shr_popup_title.setTypeface(Utility.setTypeFaceRobotoBold(getActivity()));

        /*CAMERA*/
        LinearLayout llCamera = (LinearLayout) dialogShare.findViewById(R.id.ll_take_photo);
        final TextView tvCamera = (TextView) dialogShare.findViewById(R.id.tv_take_photo);
        tvCamera.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mParent.startActivityForResult(intent, Constants.FROM_HOME_CAMERA_ID);
                dialogShare.dismiss();
            }
        });

        /*GALLERY*/
        LinearLayout llGallery = (LinearLayout) dialogShare.findViewById(R.id.ll_gallery);
        TextView tvGallery = (TextView) dialogShare.findViewById(R.id.tv_choose_gallery);
        tvGallery.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        llGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mParent.startActivityForResult(i, Constants.FROM_HOME_GALLERY_ID);
                dialogShare.dismiss();
            }
        });

          /*REMOVE*/
        LinearLayout llRemove = (LinearLayout) dialogShare.findViewById(R.id.ll_remove);
        TextView tvRemove = (TextView) dialogShare.findViewById(R.id.tv_remove);
        tvRemove.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        llRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShare.dismiss();
            }
        });
        dialogShare.show();
    }

    @Override
    public void updateProfilePic(String path) {
        Utility.showLog("Selected Image Path", "Path: " + path);

        isImgSelected = true;
        isDocSelected = false;
        isPdfSelected = false;

        mImgpath = path;
        mDocpath = "";
        mPdfpath = "";


        Bitmap bitmapImage = BitmapFactory.decodeFile(path);
        int nh = (int) (bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        scaled.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        mEncodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        mImgpathExtenstion = "";
        int i = path.lastIndexOf('.');
        if (i > 0) {
            mImgpathExtenstion = path.substring(i + 1);
        }
        ll_file_layout.setVisibility(View.VISIBLE);
        txt_path.setVisibility(View.VISIBLE);
        txt_close.setVisibility(View.VISIBLE);
        txt_path.setText("Selected Image Path: " + path);

    }

    @Override
    public void updateDoc(File path) {
        isImgSelected = false;
        isDocSelected = true;
        isPdfSelected = false;

        mImgpath = "";
        mDocpath = path.getAbsolutePath();
        mPdfpath = "";

        mDoc_extension = "";
        int i = mDocpath.lastIndexOf('.');
        if (i > 0) {
            mDoc_extension = mDocpath.substring(i + 1);
        }
        mEncodedDoc = convertFileToByteArray(path);
        ll_file_layout.setVisibility(View.VISIBLE);
        txt_path.setVisibility(View.VISIBLE);
        txt_close.setVisibility(View.VISIBLE);
        txt_path.setText("Selected File Path: " + path);

        /*final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (Integer.parseInt("220") * scale + 0.5f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.
                LayoutParams.MATCH_PARENT, pixels);
        params.setMargins(15, 15, 15, 15);
        layout_list_header.setLayoutParams(params);*/
    }

    @Override
    public void updatePdf(File path) {
        isImgSelected = false;
        isDocSelected = false;
        isPdfSelected = true;

        mImgpath = "";
        mDocpath = "";
        mPdfpath = path.getAbsolutePath();

        mDoc_extension = "";
        int i = mPdfpath.lastIndexOf('.');
        if (i > 0) {
            mDoc_extension = mPdfpath.substring(i + 1);
        }
        mEncodedDoc = convertFileToByteArray(path);
        ll_file_layout.setVisibility(View.VISIBLE);
        txt_path.setVisibility(View.VISIBLE);
        txt_close.setVisibility(View.VISIBLE);
        txt_path.setText("Selected Pdf Path: " + path);

        /*final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (Integer.parseInt("220") * scale + 0.5f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.
                LayoutParams.MATCH_PARENT, pixels);
        params.setMargins(15, 15, 15, 15);
        layout_list_header.setLayoutParams(params);*/
    }

    public static String convertFileToByteArray(File f) {
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024 * 11];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }

            byteArray = bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(byteArray, Base64.NO_WRAP);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_close:
                removeSelectedFile();
                break;
            case R.id.fab:
                openPostDialog();
                break;
        }
    }

    private void openPostDialog() {
        dialogCompleted = new Dialog(getActivity());
        dialogCompleted.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogCompleted.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCompleted.setContentView(R.layout.fragment_share);
        dialogCompleted.setCanceledOnTouchOutside(false);
        dialogCompleted.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogCompleted.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView tv_share = (TextView) dialogCompleted.findViewById(R.id.tv_share);
        tv_share.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        ImageView iv_user_profile_pic = (ImageView) dialogCompleted.findViewById(R.id.iv_user_profile_pic);
        if (!Utility.isValueNullOrEmpty(Utility.getSharedPrefStringData(getActivity(), Constants.PROFILE_PIC)))
            Picasso.with(getActivity()).load(Utility.getSharedPrefStringData(getActivity(), Constants.PROFILE_PIC)).
                    placeholder(Utility.getDrawable(getActivity(), R.drawable.avatar_image)).transform(new CircleTransform()).into(iv_user_profile_pic);

        ImageView img_close = (ImageView) dialogCompleted.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCompleted.cancel();
            }
        });

        ll_file_layout = (LinearLayout) dialogCompleted.findViewById(R.id.ll_file_layout);
        txt_path = (TextView) dialogCompleted.findViewById(R.id.txt_path);
        txt_close = (TextView) dialogCompleted.findViewById(R.id.txt_close);
        txt_close.setTypeface(Utility.setTypeFace_matirealicons(mParent));
        txt_close.setOnClickListener(this);

        TextView tv_post = (TextView) dialogCompleted.findViewById(R.id.tv_post);
        et_what_is_on_u_mind = (EditText) dialogCompleted.findViewById(R.id.et_what_is_on_u_mind);
        ImageView iv_camera = (ImageView) dialogCompleted.findViewById(R.id.iv_camera);
        ImageView iv_linked_in = (ImageView) dialogCompleted.findViewById(R.id.iv_linked_in);
        et_what_is_on_u_mind.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        tv_post.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        txt_path.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));

        tv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFields(et_what_is_on_u_mind)) {
                    postFeed(et_what_is_on_u_mind);
                }
            }
        });
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectPhotoDialog();
            }
        });
        iv_linked_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), FileChooseFragment.class);
                intent.putExtra("file", ".all");
                startActivity(intent);

               /* Bundle bundle = new Bundle();
                bundle.putString("file", ".all");
                Utility.navigateDashBoardFragment(new FileChooseFragment(), FileChooseFragment.TAG, bundle, mParent);*/
            }
        });


        dialogCompleted.show();

    }

    private void removeSelectedFile() {
        isImgSelected = false;
        isDocSelected = false;
        isPdfSelected = false;

        mImgpath = "";
        mDocpath = "";
        mPdfpath = "";

        mImgpathExtenstion = "";
        mEncodedImage = "";

        txt_path.setText("");
        txt_path.setVisibility(View.GONE);
        ll_file_layout.setVisibility(View.GONE);
        txt_close.setVisibility(View.GONE);

        /*if (dialogCompleted.isShowing()){
            dialogCompleted.cancel();
        }*/
    }

    private void removeSelectedFileDialog() {
        isImgSelected = false;
        isDocSelected = false;
        isPdfSelected = false;

        mImgpath = "";
        mDocpath = "";
        mPdfpath = "";

        mImgpathExtenstion = "";
        mEncodedImage = "";

        txt_path.setText("");
        txt_path.setVisibility(View.GONE);
        ll_file_layout.setVisibility(View.GONE);
        txt_close.setVisibility(View.GONE);

        if (dialogCompleted.isShowing()) {
            dialogCompleted.cancel();
        }
    }
}
