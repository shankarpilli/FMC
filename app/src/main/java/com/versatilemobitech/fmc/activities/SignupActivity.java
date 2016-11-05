package com.versatilemobitech.fmc.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.utility.Utility;


public class SignupActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rly_main;
    private RelativeLayout toolbar;
    private RelativeLayout rl_profile_pic;

    private LinearLayout ll_sign_up;

    private LinearLayout ll_first_name;
    private LinearLayout ll_last_name;
    private LinearLayout ll_company;
    private LinearLayout ll_business;
    private LinearLayout ll_personal;
    private LinearLayout ll_contact;
    private LinearLayout ll_alternate;
    private LinearLayout ll_location;
    private LinearLayout ll_password;
    private LinearLayout ll_c_pwd;

    private EditText edt_first_name;
    private EditText edt_last_name;
    private EditText edt_company;
    private EditText edt_business;
    private EditText edt_personal;
    private EditText edt_contact;
    private EditText edt_alternate;
    private EditText edt_location;
    private EditText edt_password;
    private EditText edt_c_pwd;

    private ImageView iv_back;
    private ImageView iv_first_name;
    private ImageView iv_last_name;
    private ImageView iv_company;
    private ImageView iv_business;
    private ImageView iv_personal;
    private ImageView iv_contact;
    private ImageView iv_alternate;
    private ImageView iv_location;
    private ImageView iv_password;
    private ImageView iv_c_pwd;
    private ImageView iv_left_profile_pic;
    private ImageView iv_right_profile_pic;

    private TextView tv_interested_location;
    private TextView tv_hyd;
    private TextView tv_chennai;
    private TextView tv_bangalore;
    private TextView tv_pune;
    private TextView txt_sign_up;

    private CheckBox cb_hyd;
    private CheckBox cb_chennai;
    private CheckBox cb_bangalore;
    private CheckBox cb_pune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initUI();
    }

    private void initUI() {

        cb_hyd = (CheckBox) findViewById(R.id.cb_hyd);
        cb_chennai = (CheckBox) findViewById(R.id.cb_chennai);
        cb_bangalore = (CheckBox) findViewById(R.id.cb_bangalore);
        cb_pune = (CheckBox) findViewById(R.id.cb_pune);

        tv_interested_location = (TextView) findViewById(R.id.tv_interested_location);
        tv_hyd = (TextView) findViewById(R.id.tv_hyd);
        tv_chennai = (TextView) findViewById(R.id.tv_chennai);
        tv_pune = (TextView) findViewById(R.id.tv_pune);
        tv_bangalore = (TextView) findViewById(R.id.tv_bangalore);
        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);

        iv_right_profile_pic = (ImageView) findViewById(R.id.iv_right_profile_pic);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        edt_first_name = (EditText)findViewById(R.id.edt_first_name);
        edt_last_name = (EditText)findViewById(R.id.edt_last_name);
        edt_company = (EditText)findViewById(R.id.edt_company_name);
        edt_business = (EditText)findViewById(R.id.edt_business_mail);
        edt_personal = (EditText)findViewById(R.id.edt_personal_mail);
        edt_contact = (EditText)findViewById(R.id.edt_contact);
        edt_alternate = (EditText)findViewById(R.id.edt_alternate);
        edt_location = (EditText)findViewById(R.id.edt_location);
        edt_password = (EditText)findViewById(R.id.edt_password);
        edt_c_pwd = (EditText)findViewById(R.id.edt_c_pwd);

        iv_right_profile_pic.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        txt_sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                super.onBackPressed();
                break;
            case R.id.iv_right_profile_pic:
                Utility.showToastMessage(SignupActivity.this, "upload pic");
                break;
            case R.id.txt_sign_up:
                if (isValied()) {
                    Utility.showToastMessage(SignupActivity.this, "Signup clicked");
                }
                break;
        }
    }

    private boolean isValied() {
        boolean isValied = false;
        if (Utility.isValueNullOrEmpty(edt_first_name.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_last_name.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_company.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_business.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_personal.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_contact.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_alternate.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_location.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_password.getText().toString().trim())) {
            isValied = false;
        } else if (Utility.isValueNullOrEmpty(edt_c_pwd.getText().toString().trim())) {
            isValied = false;
        } else if (cb_hyd.isChecked()!=true && cb_pune.isChecked()!=true && cb_chennai.isChecked()!=true && cb_bangalore.isChecked()!=true) {
            isValied = false;
        }
        else {
            isValied = true;
        }
        return isValied;
    }
}
