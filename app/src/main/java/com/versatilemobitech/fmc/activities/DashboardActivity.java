package com.versatilemobitech.fmc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.adapters.LeftMenuAdapter;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.fragments.AwardsFragment;
import com.versatilemobitech.fmc.fragments.ContactsUsFragment;
import com.versatilemobitech.fmc.fragments.EditorialsFragment;
import com.versatilemobitech.fmc.fragments.EventsFragment;
import com.versatilemobitech.fmc.fragments.GalleryFragment;
import com.versatilemobitech.fmc.fragments.HistoryFragment;
import com.versatilemobitech.fmc.fragments.HomeFragment;
import com.versatilemobitech.fmc.fragments.KnowledgePartnersFragment;
import com.versatilemobitech.fmc.fragments.LogoutFragment;
import com.versatilemobitech.fmc.fragments.MemberDirectorFragment;
import com.versatilemobitech.fmc.fragments.WelcomeFragment;
import com.versatilemobitech.fmc.models.LeftMenuModel;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.ArrayList;


public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    public DrawerLayout mDrawerLayout;
    public static ImageView iv_left_drawer_icon;
    public static TextView txt_fmc;
    private ArrayList<LeftMenuModel> leftMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_dashboard);
        initUI();
    }

    private void initUI() {
        iv_left_drawer_icon = (ImageView) findViewById(R.id.iv_left_drawer_icon);
        txt_fmc = (TextView) findViewById(R.id.txt_fmc);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_home_layout);
        assert mDrawerLayout != null;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, null, DashboardActivity.this);
        iv_left_drawer_icon.setOnClickListener(this);
        setLeftMenuData();
    }

    private void setLeftMenuData() {
        leftMenuList = new ArrayList<>();
        for (int i = 0; i < Utility.getSideMenuItemsListName().length; i++) {
            LeftMenuModel leftMenuModel = new LeftMenuModel();
            leftMenuModel.setName(Utility.getSideMenuItemsListName()[i]);
            leftMenuModel.setIcon(Utility.getSideMenuItemsListIcons()[i]);
            leftMenuList.add(leftMenuModel);
        }
        final LeftMenuAdapter leftMenuAdapter = new LeftMenuAdapter(this, leftMenuList);
        ListView list_home_left_drawer = (ListView) findViewById(R.id.list_home_left_drawer);
        list_home_left_drawer.setAdapter(leftMenuAdapter);
        setHeaderViewToList(list_home_left_drawer);
    }

    private void setHeaderViewToList(ListView list_home_left_drawer) {
        LinearLayout layout_list_header = (LinearLayout) getLayoutInflater().inflate(R.layout.
                header_leftmenu, null);
        final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (Integer.parseInt("200") * scale + 0.5f);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.
                LayoutParams.MATCH_PARENT, pixels);
        layout_list_header.setLayoutParams(params);
        ImageView img_user_image = (ImageView) layout_list_header.findViewById(R.id.img_user_image);
        if (!Utility.isValueNullOrEmpty(Utility.getSharedPrefStringData(this, Constants.PROFILE_PIC)))
            Picasso.with(this).load(Utility.getSharedPrefStringData(this, Constants.PROFILE_PIC)).
                    placeholder(Utility.getDrawable(this, R.drawable.avatar_image))
                    .transform(new CircleTransform()).into(img_user_image);
        TextView txt_name = (TextView) layout_list_header.findViewById(R.id.txt_name);
        TextView txt_user_designation = (TextView) layout_list_header.findViewById(R.id.txt_user_designation);

        txt_name.setTypeface(Utility.setTypeFaceRobotoBold(this));
        txt_name.setText(Utility.getSharedPrefStringData(DashboardActivity.this, Constants.LOGIN_NAME));
        txt_user_designation.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        txt_user_designation.setText(Utility.getSharedPrefStringData(DashboardActivity.this, Constants.COMPANY_NAME));
        list_home_left_drawer.addHeaderView(layout_list_header);

        list_home_left_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                mDrawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDrawerLayout.closeDrawers();
                        navigateSideMenuClick(position);
                    }
                }, 300);

            }
        });
    }

    private void navigateSideMenuClick(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, null, DashboardActivity.this);
                break;
            case 2:
                Utility.navigateDashBoardFragment(new WelcomeFragment(), WelcomeFragment.TAG, null, DashboardActivity.this);
                break;
            case 3:
                Utility.navigateDashBoardFragment(new HistoryFragment(), HistoryFragment.TAG, null, DashboardActivity.this);
                break;
            case 4:
                Utility.navigateDashBoardFragment(new MemberDirectorFragment(), MemberDirectorFragment.TAG, null, DashboardActivity.this);
                break;
            case 5:
                Utility.navigateDashBoardFragment(new KnowledgePartnersFragment(), KnowledgePartnersFragment.TAG, null, DashboardActivity.this);
                break;
            case 6:
                Utility.navigateDashBoardFragment(new AwardsFragment(), AwardsFragment.TAG, null, DashboardActivity.this);
                break;
            case 7:
                Utility.navigateDashBoardFragment(new GalleryFragment(), GalleryFragment.TAG, null, DashboardActivity.this);
                break;
            case 8:
                Utility.navigateDashBoardFragment(new EventsFragment(), EventsFragment.TAG, null, DashboardActivity.this);
                break;
            case 9:
                Utility.navigateDashBoardFragment(new EditorialsFragment(), EditorialsFragment.TAG, null, DashboardActivity.this);
                break;
            case 10:
                Utility.navigateDashBoardFragment(new ContactsUsFragment(), ContactsUsFragment.TAG, null, DashboardActivity.this);
                break;
            case 11:
/*                Utility.navigateDashBoardFragment(new LogoutFragment(), LogoutFragment.TAG, null, DashboardActivity.this);*/
                logOut();


                break;
        }
    }

    private void logOut() {
        Utility.setSharedPrefStringData(this, Constants.USER_ID, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_NAME, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_PASSWORD, "");
        Utility.setSharedPrefStringData(this, Constants.PREF_KEY_IS_APP_SIGNIN_OR_SIGNUP, "");
        Utility.setSharedPrefStringData(this, Constants.USER_KEY, "");
        Utility.setSharedPrefStringData(this, Constants.COMPANY_NAME, "");
        Intent mIntent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_drawer_icon:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    FragmentManager.BackStackEntry backEntry = getSupportFragmentManager()
                            .getBackStackEntryAt(
                                    getSupportFragmentManager()
                                            .getBackStackEntryCount() - 1);
                    String tagName = backEntry.getName();
                    if (!tagName.equals(HomeFragment.TAG)) {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    } else {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    }
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager()
                    .getBackStackEntryAt(
                            getSupportFragmentManager()
                                    .getBackStackEntryCount() - 1);
            String tagName = backEntry.getName();
            if (tagName.equals(HomeFragment.TAG)) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }
    }
}
