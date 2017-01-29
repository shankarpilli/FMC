package com.versatilemobitech.fmc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.ForgotPasswordModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.ForgotPasswordParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.LinkedHashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment implements View.OnClickListener, IAsyncCaller{

    public static final String TAG = "ChangePasswordFragment";
    private HomeActivity mParent;
    private Toolbar mToolbar;
    private View rootView;


    private TextView tv_change_password;
    private EditText etOldPasswordl;
    private EditText etNewPasswordl;
    private ForgotPasswordModel mForgotPasswordModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent, Utility.getResourcesString(getActivity(), R.string.change_password)));
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        rootView = inflater.inflate(R.layout.change_password, container, false);

        initUI(rootView);

        return rootView;
    }


    private void initUI(View rootView) {

        tv_change_password = (TextView) rootView.findViewById(R.id.tv_change_password);
        etOldPasswordl = (EditText)rootView.findViewById(R.id.et_old_password);
        etNewPasswordl = (EditText)rootView.findViewById(R.id.et_new_password);
        etOldPasswordl.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        etNewPasswordl.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_change_password.setTypeface(Utility.setTypeFaceRobotoRegular(mParent));
        tv_change_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_change_password:
                if (isValid()){
                    changeYourPassword();
                }
                break;
        }
    }

    private boolean isValid() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(etOldPasswordl.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, etOldPasswordl, "Please enter old password");
            etOldPasswordl.requestFocus();
        } else if (Utility.isValueNullOrEmpty(etNewPasswordl.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, etNewPasswordl, "Please enter new password");
            etNewPasswordl.requestFocus();
        } else if (etNewPasswordl.getText().toString().trim().length()<6) {
            Utility.setSnackBarEnglish(mParent, etNewPasswordl, "New password should be minimum 6 characters");
            etNewPasswordl.requestFocus();
        } else {
            isValidated = true;
        }
        return isValidated;
    }

    private void changeYourPassword() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("user_id", Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID));
        paramMap.put("oldpassword", etOldPasswordl.getText().toString());
        paramMap.put("password", etNewPasswordl.getText().toString());
        ForgotPasswordParser mForgotPasswordParser = new ForgotPasswordParser();
        if (Utility.isNetworkAvailable(getActivity())) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                    R.string.please_wait), true,
                    APIConstants.CHANGE_PASSWORD, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mForgotPasswordParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    getActivity(),
                    this.getResources().getString(
                            R.string.no_internet_msg),
                    this.getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof ForgotPasswordModel) {
                    mForgotPasswordModel = (ForgotPasswordModel) model;
                    Utility.showToastMessage(getActivity(), mForgotPasswordModel.getMessage());
                }
            }
        }
    }
}