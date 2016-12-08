package com.versatilemobitech.fmc.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.designs.MaterialDialog;
import com.versatilemobitech.fmc.interfaces.IUpdateProfilePic;
import com.versatilemobitech.fmc.models.LoginModel;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.SignUpModel;
import com.versatilemobitech.fmc.parsers.SignUpParser;
import com.versatilemobitech.fmc.permissions.Permissions;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.ImageUtility;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.IOException;
import java.util.LinkedHashMap;


public class SignupActivity extends BaseActivity implements View.OnClickListener, IAsyncCaller, IUpdateProfilePic {

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
    private EditText edt_business_mail;
    private EditText edt_personal_mail;
    private EditText edt_contact;
    private EditText edt_alternate;
    private EditText edt_location;
    private EditText edt_password;
    private EditText edt_c_pwd;
    private EditText edt_profile_pic;

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

    private Context context;
    private String mImageSelected = "";


    private static IUpdateProfilePic iUpdateProfilePic;

    public static IUpdateProfilePic getInstance() {
        return iUpdateProfilePic;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_registration);
        context = this;
        iUpdateProfilePic = this;
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

        edt_first_name = (EditText) findViewById(R.id.edt_first_name);
        edt_last_name = (EditText) findViewById(R.id.edt_last_name);
        edt_company = (EditText) findViewById(R.id.edt_company_name);
        edt_business_mail = (EditText) findViewById(R.id.edt_business_mail);
        edt_personal_mail = (EditText) findViewById(R.id.edt_personal_mail);
        edt_contact = (EditText) findViewById(R.id.edt_contact);
        edt_alternate = (EditText) findViewById(R.id.edt_alternate);
        edt_location = (EditText) findViewById(R.id.edt_location);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_c_pwd = (EditText) findViewById(R.id.edt_c_pwd);
        edt_profile_pic = (EditText) findViewById(R.id.edt_profile_pic);

        iv_right_profile_pic.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        txt_sign_up.setOnClickListener(this);
        edt_profile_pic.setOnClickListener(this);

        setTypeFace();
    }

    private void setTypeFace() {
        cb_hyd.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        cb_chennai.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        cb_bangalore.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        cb_pune.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_interested_location.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_hyd.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_chennai.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_pune.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        tv_bangalore.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        txt_sign_up.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_first_name.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_last_name.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_company.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_business_mail.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_personal_mail.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_contact.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_alternate.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_location.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_c_pwd.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        edt_password.setTypeface(Utility.setTypeFaceRobotoRegular(this));

        if (Utility.isMarshmallowOS()) {
            Permissions.getInstance().setActivity(this);
            CheckForPermissions(this, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    private void CheckForPermissions(final Context mContext, final String... mPermisons) {
        // A request for two permissions
        Permissions.getInstance().requestPermissions(new Permissions.IOnPermissionResult() {
            @Override
            public void onPermissionResult(Permissions.ResultSet resultSet) {

                if (resultSet.isPermissionGranted(Manifest.permission.CAMERA) &&
                        resultSet.isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // setUi();
                } else {
                    final MaterialDialog denyDialog = new MaterialDialog(mContext, Permissions.TITLE,
                            Permissions.MESSAGE);
                    //Positive
                    denyDialog.setAcceptButton("RE-TRY");
                    denyDialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CheckForPermissions(mContext, mPermisons);
                        }
                    });
                    denyDialog.show();
                }
            }

            @Override
            public void onRationaleRequested(Permissions.IOnRationaleProvided callback, String... permissions) {
                Permissions.getInstance().showRationaleInDialog(Permissions.TITLE,
                        Permissions.MESSAGE, "RE-TRY", callback);
            }
        }, mPermisons);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                super.onBackPressed();
                break;
            case R.id.iv_right_profile_pic:
                showSelectPhotoDialog();
                break;
            case R.id.txt_sign_up:
                if (isValidFields()) {
                    if (Utility.isNetworkAvailable(context)) {
                        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
                        paramMap.put("username", edt_personal_mail.getText().toString());
                        paramMap.put("password", edt_password.getText().toString());
                        paramMap.put("first_name", edt_first_name.getText().toString());
                        paramMap.put("last_name", edt_last_name.getText().toString());
                        paramMap.put("company_name", edt_company.getText().toString());
                        paramMap.put("profile_pic", mImageSelected);
                        paramMap.put("business_email_id", edt_business_mail.getText().toString());
                        paramMap.put("personal_email_id", edt_personal_mail.getText().toString());
                        paramMap.put("contact_number", edt_contact.getText().toString());
                        paramMap.put("alternate_contact_number", edt_alternate.getText().toString());
                        paramMap.put("current_location", edt_location.getText().toString());

                        String extension = "";
                        int i = edt_profile_pic.getText().toString().lastIndexOf('.');
                        if (i > 0) {
                            extension = edt_profile_pic.getText().toString().substring(i+1);
                        }
                        paramMap.put("file_extension", extension);
                        if (cb_hyd.isChecked() != true) {
                            paramMap.put("interested_location", "Hyderabad");
                        } else if (cb_pune.isChecked() != true) {
                            paramMap.put("interested_location", "Pune");
                        } else if (cb_chennai.isChecked() != true) {
                            paramMap.put("interested_location", "Chennai");
                        } else if (cb_bangalore.isChecked() != true) {
                            paramMap.put("interested_location", "Bangalore");
                        }
                        SignUpParser mParser = new SignUpParser();
                        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(context, Utility.getResourcesString(context,
                                R.string.please_wait), true,
                                APIConstants.REGISTRATION_URL, paramMap,
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
            case R.id.edt_profile_pic:
                showSelectPhotoDialog();
                break;
        }
    }

    private boolean isValidFields() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(edt_first_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_first_name, "Please enter first name");
            edt_first_name.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_last_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_last_name, "Please enter last name");
            edt_last_name.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_company.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_company, "Please enter company name");
            edt_company.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_company.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_company, "Please enter company name");
            edt_company.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_business_mail.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_business_mail, "Please enter business mail");
            edt_business_mail.requestFocus();
        } else if (!edt_business_mail.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_business_mail, "Please enter valid business mail");
            edt_business_mail.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_personal_mail.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_personal_mail, "Please enter personal mail");
            edt_personal_mail.requestFocus();
        } else if (!edt_personal_mail.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_personal_mail, "Please enter valid business mail");
            edt_personal_mail.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_contact.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_contact, "Please enter contact number");
            edt_contact.requestFocus();
        } else if (edt_contact.getText().toString().trim().length() != 10) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_contact, "Contact number must me 10 characteristics");
            edt_contact.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_alternate.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_alternate, "Please enter alternate contact number");
            edt_alternate.requestFocus();
        } else if (edt_alternate.getText().toString().trim().length() != 10) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_alternate, "Alternate contact number must me 10 characteristics");
            edt_alternate.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_location.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_location, "Please enter current location");
            edt_location.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_password.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_password, "Please enter password");
            edt_password.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_c_pwd.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_c_pwd, "Please enter confirm password");
            edt_c_pwd.requestFocus();
        } else if (!edt_password.getText().toString().trim().equalsIgnoreCase(edt_c_pwd.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_c_pwd, "Passwords not matched");
            edt_c_pwd.requestFocus();
        } else if (Utility.isValueNullOrEmpty(mImageSelected)) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_profile_pic, "Please upload your profile pic");
        } else if (cb_hyd.isChecked() != true && cb_pune.isChecked() != true && cb_chennai.isChecked() != true && cb_bangalore.isChecked() != true) {
            Utility.setSnackBarEnglish(SignupActivity.this, cb_hyd, "Please select insert location");
        } else {
            isValidated = true;
        }
        return isValidated;
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof SignUpModel) {
                    SignUpModel mSignUpModel = (SignUpModel) model;
                    Utility.setSharedPrefStringData(context, Constants.LOGIN_NAME, mSignUpModel.getUsername());
                    Utility.setSharedPrefStringData(context, Constants.LOGIN_PASSWORD, edt_password.getText().toString());
                    Utility.setSharedPrefStringData(this, Constants.PREF_KEY_IS_APP_SIGNIN_OR_SIGNUP, "done");
                    Utility.setSharedPrefStringData(context, Constants.COMPANY_NAME, mSignUpModel.getCompany_name());
                    Intent mIntentSignup = new Intent(SignupActivity.this, DashboardActivity.class);
                    startActivity(mIntentSignup);
                    finish();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.FROM_CAMERA_ID) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                String selectedImgPath = ImageUtility.saveBitmap(SignupActivity.this, bitmap);
                Intent intent = new Intent(SignupActivity.this, CropActivity.class);
                intent.putExtra("image_path", selectedImgPath);
                intent.putExtra("from", "RegistrationActivity");
                startActivity(intent);
            }

        } else if (requestCode == Constants.FROM_GALLERY_ID) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    String selectedImgPath = ImageUtility.saveBitmap(SignupActivity.this, bitmap);
                    Intent intent = new Intent(SignupActivity.this, CropActivity.class);
                    intent.putExtra("image_path", selectedImgPath);
                    intent.putExtra("from", "RegistrationActivity");
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void showSelectPhotoDialog() {
        final Dialog dialogShare = new Dialog(this);
        dialogShare.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogShare.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogShare.setContentView(R.layout.dialog_choose_photo_popup);
        dialogShare.getWindow().setGravity(Gravity.BOTTOM);
        dialogShare.setCanceledOnTouchOutside(true);
        dialogShare.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialogShare.getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));


        TextView txt_shr_popup_title = (TextView) dialogShare.findViewById(R.id.txt_shr_popup_title);
        txt_shr_popup_title.setTypeface(Utility.setTypeFaceRobotoBold(this));

        /*CAMERA*/
        LinearLayout llCamera = (LinearLayout) dialogShare.findViewById(R.id.ll_take_photo);
        final TextView tvCamera = (TextView) dialogShare.findViewById(R.id.tv_take_photo);
        tvCamera.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, Constants.FROM_CAMERA_ID);
                dialogShare.dismiss();
            }
        });

        /*GALLERY*/
        LinearLayout llGallery = (LinearLayout) dialogShare.findViewById(R.id.ll_gallery);
        TextView tvGallery = (TextView) dialogShare.findViewById(R.id.tv_choose_gallery);
        tvGallery.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        llGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, Constants.FROM_GALLERY_ID);
                dialogShare.dismiss();
            }
        });

          /*REMOVE*/
        LinearLayout llRemove = (LinearLayout) dialogShare.findViewById(R.id.ll_remove);
        TextView tvRemove = (TextView) dialogShare.findViewById(R.id.tv_remove);
        tvRemove.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        llRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShare.dismiss();
            }
        });
        dialogShare.show();
    }

    /*public static int getContentSizeFromUri(Context context, Uri uri) {
        String contentSize = null;
        String[] proj = {MediaStore.Images.Media.SIZE};

        CursorLoader cursorLoader = new CursorLoader(
                context,
                uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);

            if (cursor.moveToFirst())
                contentSize = cursor.getString(column_index);
        }

        return Integer.parseInt(contentSize);
    }*/

    @Override
    public void updateProfilePic(String bitmap, String path) {
        if (bitmap != null) {
            mImageSelected = bitmap;
            edt_profile_pic.setText("" + path);
        }
    }
}
