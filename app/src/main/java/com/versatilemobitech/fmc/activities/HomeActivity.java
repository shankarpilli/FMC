package com.versatilemobitech.fmc.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.Toast;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.fragments.HistoryFragment;
import com.versatilemobitech.fmc.fragments.HomeFragment;
import com.versatilemobitech.fmc.utility.Utility;

public class HomeActivity extends BaseActivity{
    public static DrawerLayout drawerLayout;
    public static Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);
/*        Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, null, HomeActivity.this);*/
        initNavigationDrawer();

    }

    public void initNavigationDrawer() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.history:
                        Utility.navigateDashBoardFragment(new HistoryFragment(), HistoryFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.member_directory:
                        Toast.makeText(getApplicationContext(),"member_directory",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
/*        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("raj.amalw@learn2crack.com");*/
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}