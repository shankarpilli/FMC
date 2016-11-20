package com.versatilemobitech.fmc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.models.LoginModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.parsers.LoginParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.LinkedHashMap;

/**
 * Created by Rev's Nani on 04-11-2016.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IAsyncCaller {

    private RelativeLayout rly_main;

    private LinearLayout ll_login;
    private LinearLayout ll_user_name;
    private LinearLayout ll_password;

    private ImageView iv_login_logo;
    private ImageView iv_user_name;
    private ImageView iv_user_line;
    private ImageView iv_password;
    private ImageView iv_pwd_line;

    private EditText et_user_name;
    private EditText et_password;

    private TextView tv_forgot_password;
    private TextView tv_login;
    private TextView tv_sign_up;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        setUI();
    }

    private void setUI() {

        rly_main = (RelativeLayout) findViewById(R.id.rly_main);

        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        ll_user_name = (LinearLayout) findViewById(R.id.ll_user_name);
        ll_password = (LinearLayout) findViewById(R.id.ll_password);

        iv_login_logo = (ImageView) findViewById(R.id.iv_login_logo);
        iv_user_name = (ImageView) findViewById(R.id.iv_user_name);
        iv_user_line = (ImageView) findViewById(R.id.iv_user_line);
        iv_password = (ImageView) findViewById(R.id.iv_password);
        iv_pwd_line = (ImageView) findViewById(R.id.iv_pwd_line);

        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_sign_up = (TextView) findViewById(R.id.tv_sign_up);

        tv_forgot_password.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_sign_up.setOnClickListener(this);

        setTypeFace();
    }

    private void setTypeFace() {
        et_user_name.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        et_password.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_forgot_password.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_login.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_sign_up.setTypeface(Utility.setTypeFaceRobotoRegular(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forgot_password:
                Intent mIntentForgotPassword = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(mIntentForgotPassword);
                break;
            case R.id.tv_login:
                if (isValid()) {
                    LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
                    paramMap.put("username", et_user_name.getText().toString());
                    paramMap.put("password", et_password.getText().toString());
                    LoginParser mParser = new LoginParser();
                    if (Utility.isNetworkAvailable(context)) {
                        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(context, Utility.getResourcesString(context,
                                R.string.please_wait), true,
                                APIConstants.LOGIN_URL, paramMap,
                                APIConstants.REQUEST_TYPE.POST, this, mParser);
                        Utility.execute(serverIntractorAsync);
                    } else {
                        Utility.showSettingDialog(
                                context,
                                context.getResources().getString(
                                        R.string.no_internet_msg),
                                context.getResources().getString(
                                        R.string.no_internet_title),
                                Utility.NO_INTERNET_CONNECTION).show();
                    }

                }
                break;
            case R.id.tv_sign_up:
                Intent mIntentSignup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(mIntentSignup);
                break;
        }
    }

    private boolean isValid() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_user_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(LoginActivity.this, et_user_name, "Please enter user name");
            et_user_name.requestFocus();
        } else if (!et_user_name.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            Utility.setSnackBarEnglish(LoginActivity.this, et_user_name, "Please enter valid user name");
            et_user_name.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_password.getText().toString().trim())) {
            Utility.setSnackBarEnglish(LoginActivity.this, et_password, "Please enter password");
            et_password.requestFocus();
        } else {
            isValidated = true;
        }
        return isValidated;
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof LoginModel) {
                    LoginModel loginModel = (LoginModel) model;
                    Utility.setSharedPrefStringData(context, Constants.USER_ID, loginModel.getUser_id());
                    Utility.setSharedPrefStringData(context, Constants.LOGIN_NAME, loginModel.getName());
                    Intent mIntentSignup = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(mIntentSignup);
                }
            }
        }
    }
}

