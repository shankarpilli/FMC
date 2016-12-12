package com.versatilemobitech.fmc.parsers;

import android.content.Context;

import com.versatilemobitech.fmc.models.GetPostsCommentModel;
import com.versatilemobitech.fmc.models.GetPostsModel;
import com.versatilemobitech.fmc.models.HomeDataModel;
import com.versatilemobitech.fmc.models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shankar on 11/20/2016.
 */
public class GetPostsParser implements Parser {

    @Override
    public Model parseResponse(String response, Context context) {
        GetPostsModel mGetPostsModel = new GetPostsModel();
        if (response != null) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                mGetPostsModel.setStatus(true);
                if (jsonObject.has("success")) {
                    mGetPostsModel.setMessage(jsonObject.optString("success"));
                }

                if (jsonObject.has("posts_details")) {
                    JSONArray mArray = jsonObject.getJSONArray("posts_details");
                    ArrayList<HomeDataModel> mList = new ArrayList<>();
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject mObj = (JSONObject) mArray.get(i);
                        HomeDataModel mHomeDataModel = new HomeDataModel();
                        mHomeDataModel.setPost_text(mObj.optString("post_text"));
                        mHomeDataModel.setUser_id(mObj.optString("user_id"));
                        mHomeDataModel.setPost_id(mObj.optString("post_id"));
                        mHomeDataModel.setFirst_name(mObj.optString("first_name"));
                        mHomeDataModel.setLast_name(mObj.optString("last_name"));
                        mHomeDataModel.setCompany_name(mObj.optString("company_name"));
                        mHomeDataModel.setProfile_pic(mObj.optString("profile_pic"));
                        JSONArray jsonCommentsArray = mObj.optJSONArray("comments");
                        ArrayList<GetPostsCommentModel> mGetPostsCommentModelList = new ArrayList<>();
                        for (int j = 0; j < jsonCommentsArray.length(); j++) {
                            JSONObject mCommentObj = (JSONObject) jsonCommentsArray.get(j);
                            GetPostsCommentModel getPostsCommentModel = new GetPostsCommentModel();
                            getPostsCommentModel.setComment(mCommentObj.optString("comment"));
                            getPostsCommentModel.setFirst_name(mCommentObj.optString("first_name"));
                            getPostsCommentModel.setLast_name(mCommentObj.optString("last_name"));
                            getPostsCommentModel.setCompany_name(mCommentObj.optString("company_name"));
                            getPostsCommentModel.setProfile_pic(mCommentObj.optString("profile_pic"));
                            getPostsCommentModel.setDatetime(mCommentObj.optString("datetime"));
                            mGetPostsCommentModelList.add(getPostsCommentModel);
                        }
                        mHomeDataModel.setGetPostsCommentModels(mGetPostsCommentModelList);
                        mList.add(mHomeDataModel);
                    }
                    mGetPostsModel.setmList(mList);
                }
            } catch (Exception e) {
                mGetPostsModel.setStatus(false);
            }
        }
        return mGetPostsModel;
    }
}
