package com.versatilemobitech.fmc.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.customviews.RoundedCornersTransformation;
import com.versatilemobitech.fmc.models.CommentsModel;
import com.versatilemobitech.fmc.models.DetailDataModel;
import com.versatilemobitech.fmc.models.DetialPostsModel;
import com.versatilemobitech.fmc.models.GetPostsCommentModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.CommentsParser;
import com.versatilemobitech.fmc.parsers.PostDetailParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

/**
 * Created by Manoj on 12/22/2016.
 */

public class DetailViewFragment extends Fragment implements IAsyncCaller, View.OnClickListener {

    public static final String TAG = "DetailViewFragment";
    private HomeActivity mParent;
    private View rootView;

    private ImageView post_image;
    private ImageView image_doc;
    private TextView txt_name;
    private TextView txt_company;
    private TextView txt_time_date;
    private TextView txt_post_message;
    //private TextView txt_total_comments;
    private TextView txt_comment;
    private TextView txt_share;
    private TextView txt_sub_name;
    private TextView txt_share_icon;
    //private TextView txt_comment_icon;
    private ImageView image_data;

    private TextView txt_send;
    private TextView edt_comment;
    private LinearLayout ll_comments;
    private LayoutInflater mLayoutInflater;

    private String mPostId = "";
    private DetailDataModel detailDataModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        mPostId = getArguments().getString("post_id");
        mLayoutInflater = (LayoutInflater) mParent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.getResourcesString(getActivity(), R.string.home));
        //mParent.txt_fmc.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        rootView = inflater.inflate(R.layout.fragment_detail_view, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        post_image = (ImageView) rootView.findViewById(R.id.post_image);
        image_doc = (ImageView) rootView.findViewById(R.id.image_doc);
        image_data = (ImageView) rootView.findViewById(R.id.image_data);
        txt_name = (TextView) rootView.findViewById(R.id.txt_name);
        txt_company = (TextView) rootView.findViewById(R.id.txt_company);
        txt_time_date = (TextView) rootView.findViewById(R.id.txt_time_date);

        //txt_total_comments = (TextView) rootView.findViewById(R.id.txt_total_comments);
        txt_comment = (TextView) rootView.findViewById(R.id.txt_comment);
        txt_share = (TextView) rootView.findViewById(R.id.txt_share);
        txt_sub_name = (TextView) rootView.findViewById(R.id.txt_sub_name);
        txt_post_message = (TextView) rootView.findViewById(R.id.txt_post_message);

        txt_share_icon = (TextView) rootView.findViewById(R.id.txt_share_icon);
        // txt_comment_icon = (TextView) rootView.findViewById(R.id.txt_comment_icon);

        txt_send = (TextView) rootView.findViewById(R.id.txt_send);
        edt_comment = (EditText) rootView.findViewById(R.id.edt_comment);
        ll_comments = (LinearLayout) rootView.findViewById(R.id.ll_comments);

        txt_name.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        txt_company.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        txt_time_date.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        txt_post_message.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        txt_sub_name.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        txt_share.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));

        // txt_total_comments.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        txt_comment.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        txt_share.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));

        txt_share_icon.setTypeface(Utility.setTypeFace_matirealicons(mParent));
        //txt_comment_icon.setTypeface(Utility.setTypeFace_matirealicons(mParent));
        txt_send.setTypeface(Utility.setTypeFace_fontawesome(mParent));
        edt_comment.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));

        getDetailViewData();

        txt_send.setOnClickListener(this);
        txt_share.setOnClickListener(this);
    }

    private void getDetailViewData() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        PostDetailParser mPostDetailParser = new PostDetailParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mParent, Utility.getResourcesString(mParent,
                    R.string.please_wait), true,
                    APIConstants.POST_DETAILS + mPostId, paramMap,
                    APIConstants.REQUEST_TYPE.GET, this, mPostDetailParser);
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
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof DetialPostsModel) {
                    DetialPostsModel mDetialPostsModel = (DetialPostsModel) model;
                    detailDataModel = mDetialPostsModel.getmList().get(0);
                    setDataToThelayout();
                } else if (model instanceof CommentsModel) {
                    CommentsModel mCommentsModel = (CommentsModel) model;
                    Utility.showToastMessage(mParent, mCommentsModel.getMessage());
                    GetPostsCommentModel getPostsCommentModel = new GetPostsCommentModel();
                    getPostsCommentModel.setProfile_pic(Utility.getSharedPrefStringData(getActivity(), Constants.PROFILE_PIC));
                    getPostsCommentModel.setFirst_name("" + Utility.getSharedPrefStringData(getActivity(), Constants.USER_NAME));
                    getPostsCommentModel.setLast_name("");
                    getPostsCommentModel.setDatetime("" + parseDOB());
                    getPostsCommentModel.setCompany_name("" + Utility.getSharedPrefStringData(getActivity(), Constants.COMPANY_NAME));
                    getPostsCommentModel.setComment("" + edt_comment.getText().toString());
                    ArrayList<GetPostsCommentModel> getPostsCommentModels = detailDataModel.getGetPostsCommentModels();
                    getPostsCommentModels.add(getPostsCommentModel);
                    detailDataModel.setGetPostsCommentModels(getPostsCommentModels);
                    edt_comment.setText("");
                    setDataToThelayout();
                }
            }
        }
    }

    public String parseDOB() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private void setDataToThelayout() {
        txt_name.setText(Utility.capitalizeFirstLetter(detailDataModel.getFirst_name())
                + " " + Utility.capitalizeFirstLetter(detailDataModel.getLast_name()));
        txt_company.setText(Utility.capitalizeFirstLetter(detailDataModel.getCompany_name()));
        txt_post_message.setText(Utility.capitalizeFirstLetter(detailDataModel.getPost_text()));
        if (!Utility.isValueNullOrEmpty(detailDataModel.getProfile_pic()))
            Picasso.with(mParent)
                    .load(detailDataModel.getProfile_pic()).transform(new RoundedCornersTransformation(10,1))
                    .placeholder(Utility.getDrawable(mParent, R.drawable.folder_icon))
                    .into(post_image);
        if (!Utility.isValueNullOrEmpty(detailDataModel.getPost_image()) && (detailDataModel.getPost_image().contains(".jpg") || detailDataModel.getPost_image().contains(".png"))) {
            image_data.setVisibility(View.VISIBLE);
            image_doc.setVisibility(View.GONE);
            Picasso.with(mParent)
                    .load(detailDataModel.getPost_image())
                    .placeholder(Utility.getDrawable(mParent, R.drawable.folder_icon))
                    .into(image_data);
        } else if (!Utility.isValueNullOrEmpty(detailDataModel.getPost_doc()) && detailDataModel.getDoc_extension().equalsIgnoreCase("pdf")) {
            image_data.setVisibility(View.GONE);
            image_doc.setVisibility(View.VISIBLE);
            image_doc.setImageDrawable(Utility.getDrawable(mParent, R.drawable.pdf_image));
        } else if (!Utility.isValueNullOrEmpty(detailDataModel.getPost_doc()) && (detailDataModel.getDoc_extension().equalsIgnoreCase("doc")
                || detailDataModel.getDoc_extension().equalsIgnoreCase("docx"))) {
            image_data.setVisibility(View.GONE);
            image_doc.setVisibility(View.VISIBLE);
            image_doc.setImageDrawable(Utility.getDrawable(mParent, R.drawable.doc_image));
        } else {
            image_data.setVisibility(View.GONE);
            image_doc.setVisibility(View.GONE);
        }

        txt_sub_name.setText(Utility.capitalizeFirstLetter(detailDataModel.getCompany_name()));
        txt_time_date.setText(Utility.capitalizeFirstLetter(detailDataModel.getDatetime()));
        if (detailDataModel.getGetPostsCommentModels().size() == 1) {
            txt_comment.setText("" + detailDataModel.getGetPostsCommentModels().size() + " comment");
        } else {
            txt_comment.setText("" + detailDataModel.getGetPostsCommentModels().size() + " comments");
        }

        image_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("file_url", detailDataModel.getPost_doc());
                Utility.navigateDashBoardFragment(new WebViewFragment(), WebViewFragment.TAG, bundle, mParent);
            }
        });

        ll_comments.removeAllViews();
        for (int i = 0; i < detailDataModel.getGetPostsCommentModels().size(); i++) {
            GetPostsCommentModel getPostsCommentModel = detailDataModel.getGetPostsCommentModels().get(i);
            LinearLayout layout_list_header = (LinearLayout) mLayoutInflater.inflate(R.layout.
                    home_comment_item, null);
            TextView txt_comment_name = (TextView) layout_list_header.findViewById(R.id.txt_comment_name);
            TextView txt_comment_company = (TextView) layout_list_header.findViewById(R.id.txt_comment_company);
            TextView txt_comment_time_date = (TextView) layout_list_header.findViewById(R.id.txt_comment_time_date);
            TextView txt_comment_post_message = (TextView) layout_list_header.findViewById(R.id.txt_comment_post_message);
            TextView txt_reply = (TextView) layout_list_header.findViewById(R.id.txt_reply);
            ImageView img_commented = (ImageView) layout_list_header.findViewById(R.id.img_commented);

            txt_comment_name.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
            txt_comment_company.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
            txt_comment_time_date.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
            txt_comment_post_message.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
            txt_reply.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));

            if (!Utility.isValueNullOrEmpty(getPostsCommentModel.getProfile_pic()))
                Picasso.with(getActivity()).load(Utility.getSharedPrefStringData(getActivity(), Constants.PROFILE_PIC)).
                        placeholder(Utility.getDrawable(getActivity(), R.drawable.avatar_image))
                        .transform(new CircleTransform()).into(img_commented);
            txt_comment_name.setText(getPostsCommentModel.getFirst_name());
            txt_comment_company.setText(getPostsCommentModel.getCompany_name());
            txt_comment_time_date.setText(getPostsCommentModel.getDatetime());
            txt_comment_post_message.setText(getPostsCommentModel.getComment());
            ll_comments.addView(layout_list_header);
        }

    }


    /*private void showShareDialog(String data, String link) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "FM Council");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(new StringBuilder()
                .append(data)
                .append('\n')
                .append(link)
                .toString()));
        getActivity().startActivity(Intent.createChooser(shareIntent, "Share Using"));
    }*/


    private void showShareDialog(String data) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "FM Council");
        shareIntent
                .putExtra(
                        Intent.EXTRA_TEXT,
                       data);
        startActivity(Intent.createChooser(shareIntent, "Share Using"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_send:
                commentOnPost();
                break;
            case R.id.txt_share:
                if (!Utility.isValueNullOrEmpty(detailDataModel.getPost_image()) &&
                        (detailDataModel.getPost_image().contains(".jpg") || detailDataModel.getPost_image().contains(".png"))) {
                    /*showShareDialog(detailDataModel.getPost_text(), detailDataModel.getPost_image());*/
                    showShareDialog(detailDataModel.getPost_text() + " " + detailDataModel.getPost_image());
                } else if (!Utility.isValueNullOrEmpty(detailDataModel.getPost_doc()) && detailDataModel.getDoc_extension().equalsIgnoreCase("pdf")) {
                    showShareDialog(detailDataModel.getPost_text() + " " + detailDataModel.getPost_doc());
                } else if (!Utility.isValueNullOrEmpty(detailDataModel.getPost_doc()) && (detailDataModel.getDoc_extension().equalsIgnoreCase("doc")
                        || detailDataModel.getDoc_extension().equalsIgnoreCase("docx"))) {
                    showShareDialog(detailDataModel.getPost_text() + " " + detailDataModel.getPost_doc());
                } else {
                    showShareDialog(detailDataModel.getPost_text());
                }
                break;
        }
    }

    private void commentOnPost() {
        if (Utility.isValueNullOrEmpty(edt_comment.getText().toString())) {
            Utility.setSnackBarEnglish(mParent, edt_comment, "Please Enter comment");
        } else {
            LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
            paramMap.put("post_id", mPostId);
            paramMap.put("user_id", Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID));
            paramMap.put("comment", edt_comment.getText().toString());
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
    }


}
