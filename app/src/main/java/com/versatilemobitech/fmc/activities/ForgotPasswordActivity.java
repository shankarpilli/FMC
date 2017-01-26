package com.versatilemobitech.fmc.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.ForgotPasswordModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.ForgotPasswordParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.LinkedHashMap;


public class ForgotPasswordActivity extends BaseActivity implements IAsyncCaller {

    private EditText etUserName;
    private TextView txtGetPassword;
    private ForgotPasswordModel mForgotPasswordModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        intUI();

    }

    private void intUI() {
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etUserName.setTypeface(Utility.setTypeFaceRobotoRegular(this));

        txtGetPassword = (TextView)findViewById(R.id.tv_get_password);
        txtGetPassword.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        txtGetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isValueNullOrEmpty(etUserName.getText().toString().trim())) {
                    Utility.setSnackBarEnglish(ForgotPasswordActivity.this, etUserName, "Please enter user name");
                    etUserName.requestFocus();
                } else {
                    //finish();
                    getYourPassword();
                }
            }
        });
    }

    private void getYourPassword() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("username", etUserName.getText().toString());
        ForgotPasswordParser mForgotPasswordParser = new ForgotPasswordParser();
        if (Utility.isNetworkAvailable(this)) {
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(this, Utility.getResourcesString(this,
                    R.string.please_wait), true,
                    APIConstants.FORGOT_PASSWORD , paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mForgotPasswordParser);
            Utility.execute(serverIntractorAsync);
        } else {
            Utility.showSettingDialog(
                    this,
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
                    Utility.showToastMessage(this, mForgotPasswordModel.getMessage());
                    finish();
                }
            }
        }
    }
}
