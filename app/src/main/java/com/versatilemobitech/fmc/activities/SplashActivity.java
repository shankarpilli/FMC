package com.versatilemobitech.fmc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;


/**
 * Created by Shankar Pilli on 20-10-2016.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_splash);

        Handler mSplashHandler = new Handler();
        Runnable action = new Runnable() {
            @Override
            public void run() {
                if (Utility.isValueNullOrEmpty(Utility.getSharedPrefStringData(SplashActivity.this, Constants.PREF_KEY_IS_APP_SIGNIN_OR_SIGNUP))) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        mSplashHandler.postDelayed(action, Constants.SPLASH_TIME_OUT);
    }
}
