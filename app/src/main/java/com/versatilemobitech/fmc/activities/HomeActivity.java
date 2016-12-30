package com.versatilemobitech.fmc.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.versatilemobitech.fmc.R;


public class HomeActivity extends Activity {

    private DrawerLayout drawer_layout;
    private Toolbar toolbar;
    private NavigationView navigation_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigation_view = (NavigationView)findViewById(R.id.navigation_view);
    }
}
