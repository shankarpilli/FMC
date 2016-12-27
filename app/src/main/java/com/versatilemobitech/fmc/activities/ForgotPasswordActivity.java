package com.versatilemobitech.fmc.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.utility.Utility;


public class ForgotPasswordActivity extends BaseActivity {

    private EditText etUserName;
    private TextView txtGetPassword;
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
                }else {
                    finish();
                }
            }
        });
    }
}
