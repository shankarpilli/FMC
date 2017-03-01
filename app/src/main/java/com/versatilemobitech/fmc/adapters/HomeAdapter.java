package com.versatilemobitech.fmc.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.isseiaoki.simplecropview.util.Utils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.customviews.RoundedCornersTransformation;
import com.versatilemobitech.fmc.fragments.DetailViewFragment;
import com.versatilemobitech.fmc.fragments.WebViewFragment;
import com.versatilemobitech.fmc.models.GetPostsModel;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.PostLikeModel;
import com.versatilemobitech.fmc.parsers.PostLikeParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar on 11/6/2016.
 */
public class HomeAdapter extends BaseAdapter implements IAsyncCaller {

    private Context mContext;
    private HomeActivity dashboardActivity;
    private LayoutInflater mLayoutInflater;
    private ArrayList<HomeDataModel> homeDataModels;
    private Fragment fragment;
    private int mCurrentLikePosition = -1;

    private Typeface mTypeFaceRobotoRegular;
    private Typeface mTypeFaceRobotoLight;

    public HomeAdapter(HomeActivity dashboardActivity, Context context, Fragment fragment, ArrayList<HomeDataModel> homeDataModels) {
        mContext = context;
        this.dashboardActivity = dashboardActivity;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.homeDataModels = homeDataModels;
        this.fragment = fragment;
        this.mTypeFaceRobotoRegular = Utility.setTypeFaceRobotoRegular(mContext);
        this.mTypeFaceRobotoLight = Utility.setTypeRobotoLight(mContext);
    }


    @Override
    public int getCount() {
        return homeDataModels.size();
    }

    @Override
    public HomeDataModel getItem(int position) {
        return homeDataModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        HomeItemHolder mHomeItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.home_data_item,
                    null);
            mHomeItemHolder = new HomeItemHolder();
            mHomeItemHolder.post_image = (ImageView) convertView.findViewById(R.id.post_image);
            mHomeItemHolder.image_doc = (ImageView) convertView.findViewById(R.id.image_doc);
            mHomeItemHolder.doc_progressBar_image = (ProgressBar) convertView.findViewById(R.id.doc_progressBar_image);
            mHomeItemHolder.image_data = (ImageView) convertView.findViewById(R.id.image_data);
            mHomeItemHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            mHomeItemHolder.txt_company = (TextView) convertView.findViewById(R.id.txt_company);
            mHomeItemHolder.txt_time_date = (TextView) convertView.findViewById(R.id.txt_time_date);

            mHomeItemHolder.txt_total_comments = (TextView) convertView.findViewById(R.id.txt_total_comments);
            mHomeItemHolder.txt_total_likes = (TextView) convertView.findViewById(R.id.txt_total_likes);
            mHomeItemHolder.ll_like = (LinearLayout) convertView.findViewById(R.id.ll_like);
            mHomeItemHolder.txt_like = (TextView) convertView.findViewById(R.id.txt_like);
            mHomeItemHolder.txt_comment = (TextView) convertView.findViewById(R.id.txt_comment);
            mHomeItemHolder.txt_share = (TextView) convertView.findViewById(R.id.txt_share);
            mHomeItemHolder.txt_sub_name = (TextView) convertView.findViewById(R.id.txt_sub_name);
            mHomeItemHolder.txt_post_message = (TextView) convertView.findViewById(R.id.txt_post_message);

            mHomeItemHolder.ll_like_layout = (LinearLayout) convertView.findViewById(R.id.ll_like_layout);
            mHomeItemHolder.txt_recently_liked = (TextView) convertView.findViewById(R.id.txt_recently_liked);
            mHomeItemHolder.txt_likes_this = (TextView) convertView.findViewById(R.id.txt_likes_this);
            mHomeItemHolder.view_like_line = (View) convertView.findViewById(R.id.view_like_line);

            //mHomeItemHolder.txt_share_icon = (TextView) convertView.findViewById(R.id.txt_share_icon);
            //mHomeItemHolder.txt_comment_icon = (TextView) convertView.findViewById(R.id.txt_comment_icon);

            mHomeItemHolder.txt_name.setTypeface(mTypeFaceRobotoRegular);
            mHomeItemHolder.txt_company.setTypeface(mTypeFaceRobotoRegular);
            mHomeItemHolder.txt_time_date.setTypeface(mTypeFaceRobotoRegular);
            mHomeItemHolder.txt_post_message.setTypeface(mTypeFaceRobotoLight);
            mHomeItemHolder.txt_sub_name.setTypeface(mTypeFaceRobotoLight);
            mHomeItemHolder.txt_share.setTypeface(mTypeFaceRobotoRegular);

            mHomeItemHolder.txt_total_comments.setTypeface(mTypeFaceRobotoLight);
            mHomeItemHolder.txt_total_likes.setTypeface(mTypeFaceRobotoLight);
            mHomeItemHolder.txt_comment.setTypeface(mTypeFaceRobotoLight);
            mHomeItemHolder.txt_share.setTypeface(mTypeFaceRobotoLight);

            mHomeItemHolder.txt_recently_liked.setTypeface(mTypeFaceRobotoRegular);
            mHomeItemHolder.txt_likes_this.setTypeface(mTypeFaceRobotoLight);

            //mHomeItemHolder.txt_share_icon.setTypeface(Utility.setTypeFace_matirealicons(mContext));
            //mHomeItemHolder.txt_comment_icon.setTypeface(Utility.setTypeFace_matirealicons(mContext));

            convertView.setTag(mHomeItemHolder);
        } else {
            mHomeItemHolder = (HomeItemHolder) convertView.getTag();
        }

        HomeDataModel mHomeDataModel = (HomeDataModel) getItem(position);

        mHomeItemHolder.txt_name.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getFirst_name())
                + " " + Utility.capitalizeFirstLetter(mHomeDataModel.getLast_name()));
        mHomeItemHolder.txt_company.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getCompany_name()));
        if (Utility.isValueNullOrEmpty(mHomeDataModel.getPost_text())) {
            mHomeItemHolder.txt_post_message.setVisibility(View.GONE);
        } else {
            mHomeItemHolder.txt_post_message.setVisibility(View.VISIBLE);
            mHomeItemHolder.txt_post_message.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getPost_text()));
        }

        if (Utility.isValueNullOrEmpty(mHomeDataModel.getRecently_liked())) {
            mHomeItemHolder.ll_like_layout.setVisibility(View.GONE);
            mHomeItemHolder.view_like_line.setVisibility(View.GONE);
        } else {
            mHomeItemHolder.ll_like_layout.setVisibility(View.VISIBLE);
            mHomeItemHolder.view_like_line.setVisibility(View.VISIBLE);
            mHomeItemHolder.txt_recently_liked.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getRecently_liked()));
        }

        if (!Utility.isValueNullOrEmpty(mHomeDataModel.getProfile_pic()))
            Picasso.with(mContext)
                    .load(mHomeDataModel.getProfile_pic())
                    .transform(new RoundedCornersTransformation(5, 1))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                    .into(mHomeItemHolder.post_image);
        if (!Utility.isValueNullOrEmpty(mHomeDataModel.getPost_image()) && (mHomeDataModel.getPost_image().contains(".jpg") || mHomeDataModel.getPost_image().contains(".png"))) {
            mHomeItemHolder.image_data.setVisibility(View.VISIBLE);
            mHomeItemHolder.image_doc.setVisibility(View.GONE);
            mHomeItemHolder.doc_progressBar_image.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Utility.getDeviceWidth(dashboardActivity),
                    Utility.getDeviceWidth(dashboardActivity));
            mHomeItemHolder.image_data.setLayoutParams(params);
            Picasso.with(mContext)
                    .load(mHomeDataModel.getPost_image())
                    .placeholder(Utility.getDrawable(mContext, R.drawable.folder_icon))
                    .into(mHomeItemHolder.image_data);
        } else if (!Utility.isValueNullOrEmpty(mHomeDataModel.getPost_doc()) && mHomeDataModel.getDoc_extension().equalsIgnoreCase("pdf")) {
            mHomeItemHolder.image_data.setVisibility(View.GONE);
            mHomeItemHolder.image_doc.setVisibility(View.VISIBLE);
            //mHomeItemHolder.doc_progressBar_image.setVisibility(View.VISIBLE);
            mHomeItemHolder.image_doc.setImageDrawable(Utility.getDrawable(mContext, R.drawable.pdf_image));
        } else if (!Utility.isValueNullOrEmpty(mHomeDataModel.getPost_doc()) && (mHomeDataModel.getDoc_extension().equalsIgnoreCase("doc")
                || mHomeDataModel.getDoc_extension().equalsIgnoreCase("docx"))) {
            mHomeItemHolder.image_data.setVisibility(View.GONE);
            mHomeItemHolder.image_doc.setVisibility(View.VISIBLE);
            //mHomeItemHolder.doc_progressBar_image.setVisibility(View.VISIBLE);
            mHomeItemHolder.image_doc.setImageDrawable(Utility.getDrawable(mContext, R.drawable.doc_image));
        } else {
            mHomeItemHolder.image_data.setVisibility(View.GONE);
            mHomeItemHolder.image_doc.setVisibility(View.GONE);
        }

        mHomeItemHolder.txt_sub_name.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getCompany_name()));
        mHomeItemHolder.txt_time_date.setText(Utility.capitalizeFirstLetter(mHomeDataModel.getDatetime()));
        if (mHomeDataModel.getComments_count() == 1) {
            mHomeItemHolder.txt_total_comments.setText("" + mHomeDataModel.getComments_count() + " comment");
        } else {
            mHomeItemHolder.txt_total_comments.setText("" + mHomeDataModel.getComments_count() + " comments");
        }

        if (mHomeDataModel.getLikes_count() == 1) {
            mHomeItemHolder.txt_total_likes.setText("" + mHomeDataModel.getLikes_count() + " like");
        } else {
            mHomeItemHolder.txt_total_likes.setText("" + mHomeDataModel.getLikes_count() + " likes");
        }


        mHomeItemHolder.image_doc.setTag(position);
        final HomeItemHolder finalMHomeItemHolder = mHomeItemHolder;
        mHomeItemHolder.image_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tagPosition = (int) view.getTag();
                Bundle bundle = new Bundle();
                bundle.putString("file_url", homeDataModels.get(tagPosition).getPost_doc());
                String mFileName = homeDataModels.get(tagPosition).getPost_doc().replace("http://facilitymanagementcouncil.com/admin/uploads/postfiles/", "");
                //Utility.navigateDashBoardFragment(new WebViewFragment(), WebViewFragment.TAG, bundle, dashboardActivity);
                /*File temp_file = new File(homeDataModels.get(tagPosition).getPost_doc());
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(temp_file), getMimeType(temp_file.getAbsolutePath()));
                mContext.startActivity(intent);*/
                new DownloadFileFromURL(mFileName, finalMHomeItemHolder.doc_progressBar_image).execute(homeDataModels.get(tagPosition).getPost_doc());
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("post_id", homeDataModels.get(position).getPost_id());
                Utility.navigateDashBoardFragment(new DetailViewFragment(), DetailViewFragment.TAG, bundle, dashboardActivity);
            }
        });

        mHomeItemHolder.txt_share.setTag(position);
        mHomeItemHolder.txt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tagPosition = (int) view.getTag();
                if (!Utility.isValueNullOrEmpty(homeDataModels.get(tagPosition).getPost_image()) &&
                        (homeDataModels.get(tagPosition).getPost_image().contains(".jpg") || homeDataModels.get(tagPosition).getPost_image().contains(".png"))) {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text() + " " + homeDataModels.get(tagPosition).getPost_image());
                } else if (!Utility.isValueNullOrEmpty(homeDataModels.get(tagPosition).getPost_doc()) && homeDataModels.get(tagPosition).getDoc_extension().equalsIgnoreCase("pdf")) {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text() + " " + homeDataModels.get(tagPosition).getPost_doc());
                } else if (!Utility.isValueNullOrEmpty(homeDataModels.get(tagPosition).getPost_doc()) && (homeDataModels.get(tagPosition).getDoc_extension().equalsIgnoreCase("doc")
                        || homeDataModels.get(tagPosition).getDoc_extension().equalsIgnoreCase("docx"))) {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text() + " " + homeDataModels.get(tagPosition).getPost_doc());
                } else {
                    showShareDialog(homeDataModels.get(tagPosition).getPost_text());
                }
            }
        });

        mHomeItemHolder.txt_like.setTypeface(mTypeFaceRobotoLight);
        mHomeItemHolder.ll_like.setId(position);
        mHomeItemHolder.ll_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getId();
                mCurrentLikePosition = position;
                String mStringId = homeDataModels.get(position).getPost_id();
                if (homeDataModels.get(position).getAlready_liked() == 0)
                    postLike(mStringId);
                else
                    Utility.showToastMessage(mContext, Utility.getResourcesString(mContext, R.string.you_have_already_liked_this_post));
            }
        });

        return convertView;
    }

    private void postLike(String post_id) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("user_id", Utility.getSharedPrefStringData(mContext, Constants.USER_ID));
        paramMap.put("post_id", post_id);
        paramMap.put("like", "1");
        PostLikeParser postLikeParser = new PostLikeParser();
        if (Utility.isNetworkAvailable(mContext)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(mContext, Utility.getResourcesString(mContext,
                    R.string.please_wait), true,
                    APIConstants.POST_LIKE, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, postLikeParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    mContext,
                    mContext.getResources().getString(
                            R.string.no_internet_msg),
                    mContext.getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }

    }

    private void showShareDialog(String data) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "FM Council");
        shareIntent
                .putExtra(
                        Intent.EXTRA_TEXT,
                        data);
        mContext.startActivity(Intent.createChooser(shareIntent, "Share Using"));
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof PostLikeModel) {
                    PostLikeModel postLikeModel = (PostLikeModel) model;
                    Utility.showLog("mCurrentLikePosition", "" + mCurrentLikePosition);
                    Utility.showToastMessage(mContext, postLikeModel.getMessage());
                    Utility.showLog("Log", "" + mCurrentLikePosition);
                    HomeDataModel homeDataModel = homeDataModels.get(mCurrentLikePosition);
                    homeDataModel.setLikes_count(homeDataModel.getLikes_count() + 1);
                    homeDataModel.setAlready_liked(homeDataModel.getAlready_liked() + 1);
                    homeDataModels.set(mCurrentLikePosition, homeDataModel);
                    notifyDataSetChanged();
                }
            }
        }
    }

    private class HomeItemHolder {
        private ImageView post_image;
        private ImageView image_doc;
        private TextView txt_name;
        private TextView txt_company;
        private TextView txt_time_date;
        private TextView txt_post_message;
        private TextView txt_total_comments;
        private TextView txt_total_likes;
        private TextView txt_comment;
        private TextView txt_share;
        private TextView txt_like;
        private TextView txt_sub_name;
        //private TextView txt_share_icon;
        //private TextView txt_comment_icon;
        private ImageView image_data;
        private LinearLayout ll_like;

        private LinearLayout ll_like_layout;
        private TextView txt_recently_liked;
        private TextView txt_likes_this;
        private View view_like_line;

        private ProgressBar doc_progressBar_image;
    }

    private String getMimeType(String url) {
        String parts[] = url.split("\\.");
        String extension = parts[parts.length - 1];
        String type = null;
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
        }
        return type;
    }


    private class DownloadFileFromURL extends AsyncTask<String, String, String> {

        private String mUrl = "";
        private String mFileName = "";
        private String filenameOutput;
        private File audioFileOutput;
        private ProgressBar mProgressBar;

        DownloadFileFromURL(String mFileName, ProgressBar mProgressBar) {
            this.mProgressBar = mProgressBar;
            this.mFileName = mFileName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                mUrl = f_url[0];
                URLConnection connection = url.openConnection();
                connection.connect();

                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                File DIRECTORY = new File(Environment.getExternalStorageDirectory() + "/" + Constants.DIRECTORY/*+"/"+CommonSettings.FOLDER_MEDIA*/);
                if (!DIRECTORY.exists()) {
                    DIRECTORY.mkdir();
                }
                File FOLDER = null;
                String strDir;
                if (url.toString().endsWith(".doc") || url.toString().endsWith(".docx")) {
                    strDir = Environment.getExternalStorageDirectory() + "/" + Constants.DIRECTORY + "/" + Constants.DOCS;
                    FOLDER = new File(strDir);
                    filenameOutput = mFileName;
                }
                if (FOLDER != null && !FOLDER.exists()) {
                    FOLDER.mkdir();
                }
                audioFileOutput = new File(FOLDER, filenameOutput);
                // Output stream to write file
                OutputStream output = new FileOutputStream(audioFileOutput);

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    if (!isCancelled()) {
                        total += count;
                        publishProgress("" + (int) ((total * 100) / lengthOfFile));
                        output.write(data, 0, count);
                    }
                }
                // flushing output
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                e.printStackTrace();
                Utility.showLog("Error: ", e.getMessage());
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {
            /*mProgressBar.setText("" + Integer.parseInt(progress[0]), mContext.getResources().getColor(R.color.bright_orange));*/
            mProgressBar.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String file_url) {
            String mfilePath;
            mfilePath = Environment.getExternalStorageDirectory() + "/" + Constants.DIRECTORY + "/" + Constants.DOCS + "/" + filenameOutput;
            Utility.showLog("file_url", "" + file_url);
            File temp_file = new File(mfilePath);
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(temp_file), getMimeType(temp_file.getAbsolutePath()));
            mContext.startActivity(intent);
            mProgressBar.setVisibility(View.GONE);
        }
    }

}
