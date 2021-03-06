package com.versatilemobitech.fmc.activities;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.customviews.CustomTypefaceSpan;
import com.versatilemobitech.fmc.fragments.AwardsFragment;
import com.versatilemobitech.fmc.fragments.CSRFragment;
import com.versatilemobitech.fmc.fragments.ChangePasswordFragment;
import com.versatilemobitech.fmc.fragments.ContactsUsFragment;
import com.versatilemobitech.fmc.fragments.EditProfileFragment;
import com.versatilemobitech.fmc.fragments.EditorialsFragment;
import com.versatilemobitech.fmc.fragments.EventsFragment;
import com.versatilemobitech.fmc.fragments.GalleryFragment;
import com.versatilemobitech.fmc.fragments.HistoryFragment;
import com.versatilemobitech.fmc.fragments.HomeFragment;
import com.versatilemobitech.fmc.fragments.MemberDirectorFragment;
import com.versatilemobitech.fmc.fragments.VendorPartnersFragment;
import com.versatilemobitech.fmc.fragments.WelcomeFragment;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.ImageUtility;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.IOException;

public class HomeActivity extends BaseActivity {

    public static DrawerLayout drawerLayout;
    public static Toolbar toolbar;
    private NavigationView navigationView;
    public Dialog progressDialog;
    private static long back_pressed;

    public static TextView txt_name;
    public static TextView txt_user_designation;
    public static ImageView img_user_image;

    private String postId = "";
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null) {
            postId = getIntent().getStringExtra(Constants.POST_ID);
            type = getIntent().getStringExtra(Constants.TYPE);
        }

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }

        setSupportActionBar(toolbar);
        initNavigationDrawer();

    }

    public void initNavigationDrawer() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.POST_ID, postId);
        bundle.putString(Constants.TYPE, type);
        Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, bundle, HomeActivity.this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.home:
                        Utility.navigateDashBoardFragment(new HomeFragment(), null, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.welcome_message:
                        Utility.navigateDashBoardFragment(new WelcomeFragment(), WelcomeFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.history:
                        Utility.navigateDashBoardFragment(new HistoryFragment(), HistoryFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.member_directory:
                        Utility.navigateDashBoardFragment(new MemberDirectorFragment(), MemberDirectorFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.vendor_partners:
                        Utility.navigateDashBoardFragment(new VendorPartnersFragment(), VendorPartnersFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.awards:
                        Utility.navigateDashBoardFragment(new AwardsFragment(), AwardsFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.csr:
                        Utility.navigateDashBoardFragment(new CSRFragment(), CSRFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.gallery:
                        Utility.navigateDashBoardFragment(new GalleryFragment(), GalleryFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.events:
                        Utility.navigateDashBoardFragment(new EventsFragment(), EventsFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.editorials:
                        Utility.navigateDashBoardFragment(new EditorialsFragment(), EditorialsFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.contact_us:
                        Utility.navigateDashBoardFragment(new ContactsUsFragment(), ContactsUsFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        logOut();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.change_password:
                        //drawerLayout.closeDrawers();
                        Utility.navigateDashBoardFragment(new ChangePasswordFragment(), ChangePasswordFragment.TAG, null, HomeActivity.this);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        View header = navigationView.getHeaderView(0);
        img_user_image = (ImageView) header.findViewById(R.id.img_user_image);
        Utility.showLog("Data", Utility.getSharedPrefStringData(this, Constants.PROFILE_PIC));
        if (!Utility.isValueNullOrEmpty(Utility.getSharedPrefStringData(this, Constants.PROFILE_PIC)))
            Picasso.with(this).load(Utility.getSharedPrefStringData(this, Constants.PROFILE_PIC))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE).
                    placeholder(Utility.getDrawable(this, R.drawable.avatar_image))
                    .transform(new CircleTransform()).into(img_user_image);
        txt_name = (TextView) header.findViewById(R.id.txt_name);
        txt_user_designation = (TextView) header.findViewById(R.id.txt_user_designation);
        TextView tv_edit = (TextView) header.findViewById(R.id.tv_edit);

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.navigateDashBoardFragment(new EditProfileFragment(), EditProfileFragment.TAG, null, HomeActivity.this);
                drawerLayout.closeDrawers();
            }
        });

        txt_name.setTypeface(Utility.setTypeRobotoBoldRegular(this));
        tv_edit.setTypeface(Utility.setTypeFace_fontawesome(this));
        txt_name.setText(Utility.capitalizeFirstLetter(Utility.getSharedPrefStringData(HomeActivity.this, Constants.FIRST_NAME)) + " "
                + Utility.capitalizeFirstLetter(Utility.getSharedPrefStringData(HomeActivity.this, Constants.LAST_NAME)));
        txt_user_designation.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        txt_user_designation.setText(Utility.getSharedPrefStringData(HomeActivity.this, Constants.COMPANY_NAME));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        final View.OnClickListener originalToolbarListener = actionBarDrawerToggle.getToolbarNavigationClickListener();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getSupportFragmentManager().popBackStack();
                        }
                    });
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                    actionBarDrawerToggle.setToolbarNavigationClickListener(originalToolbarListener);
                }
            }
        });
    }

    private void logOut() {
        showExitDialog("logOut");
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.FROM_HOME_CAMERA_ID) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImageUri = data.getData();
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                String selectedImgPath = ImageUtility.saveBitmap(HomeActivity.this, bitmap);
                HomeFragment.getInstance().updateProfilePic(selectedImgPath);
            }

        } else if (requestCode == Constants.FROM_EDIT_CAMERA_ID) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                String selectedImgPath = ImageUtility.saveBitmap(HomeActivity.this, bitmap);
                Intent intent = new Intent(HomeActivity.this, CropActivity.class);
                intent.putExtra("image_path", selectedImgPath);
                intent.putExtra("from", "EditProfileFragment");
                startActivity(intent);
            }

        } else if (requestCode == Constants.FROM_HOME_GALLERY_ID) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    String selectedImgPath = ImageUtility.saveBitmap(HomeActivity.this, bitmap);
                    HomeFragment.getInstance().updateProfilePic(getRealPathFromURI(selectedImageUri));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == Constants.FROM_EDIT_GALLERY_ID) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImageUri = data.getData();
                try {
                    Intent intent = new Intent(HomeActivity.this, CropActivity.class);
                    intent.putExtra("image_path", getRealPathFromURI(selectedImageUri));
                    intent.putExtra("from", "EditProfileFragment");
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public String getRealPathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
                // finishAffinity();
                showExitDialog("Back");
            } else {
                super.onBackPressed();
            }
        }
    }

    private void showExitDialog(String mFrom) {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            showConformationDialog(mFrom);
        }
        back_pressed = System.currentTimeMillis();
    }

    private void showConformationDialog(final String mFrom) {
        final Dialog dialogEventConfirmation = new Dialog(this);
        dialogEventConfirmation.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogEventConfirmation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEventConfirmation.setContentView(R.layout.dialog_exit_confirmation);
        //dialogEventConfirmation.getWindow().setGravity(Gravity.BOTTOM);
        dialogEventConfirmation.setCanceledOnTouchOutside(true);
        //dialogEventConfirmation.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialogEventConfirmation.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView txt_dialog_message = (TextView) dialogEventConfirmation.findViewById(R.id.txt_dialog_message);
        TextView tv_yes = (TextView) dialogEventConfirmation.findViewById(R.id.tv_yes);
        TextView tv_no = (TextView) dialogEventConfirmation.findViewById(R.id.tv_no);

        if (mFrom.equals("logOut")) {
            txt_dialog_message.setTextSize(18);
            txt_dialog_message.setText("" + Utility.getResourcesString(this, R.string.are_you_sure_do_you_want_to_logout));
        } else {
            txt_dialog_message.setText("" + Utility.getResourcesString(this, R.string.are_you_sure_do_you_want_to_exit));
        }

        txt_dialog_message.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_yes.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_no.setTypeface(Utility.setTypeFaceRobotoRegular(this));

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEventConfirmation.dismiss();
                if (mFrom.equals("logOut")) {
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.USER_ID, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.LOGIN_NAME, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.LOGIN_PASSWORD, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.PREF_KEY_IS_APP_SIGNIN_OR_SIGNUP, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.USER_KEY, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.COMPANY_NAME, "");

                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.FIRST_NAME, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.LAST_NAME, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.BUSINESS_MAIL_ID, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.PERSONAL_MAIL_ID, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.CONTACT, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.ALTERNATE, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.CURRENT_LOCATION, "");
                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.INTERESTED_LOCATION, "");

                    Utility.setSharedPrefStringData(HomeActivity.this, Constants.DEVICE_TOKEN, "");

                    Intent mIntent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                }
                finish();
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEventConfirmation.dismiss();
            }
        });

        dialogEventConfirmation.show();
    }
}