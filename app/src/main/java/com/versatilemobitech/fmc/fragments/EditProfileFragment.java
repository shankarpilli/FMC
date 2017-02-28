package com.versatilemobitech.fmc.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.activities.HomeActivity;
import com.versatilemobitech.fmc.activities.SignupActivity;
import com.versatilemobitech.fmc.asynctask.IAsyncCaller;
import com.versatilemobitech.fmc.asynctask.ServerIntractorAsync;
import com.versatilemobitech.fmc.customviews.CircleTransform;
import com.versatilemobitech.fmc.interfaces.IUpdateSelectedPic;
import com.versatilemobitech.fmc.models.Model;
import com.versatilemobitech.fmc.models.SignUpModel;
import com.versatilemobitech.fmc.parsers.SignUpParser;
import com.versatilemobitech.fmc.utility.APIConstants;
import com.versatilemobitech.fmc.utility.Constants;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener, IAsyncCaller, IUpdateSelectedPic {

    public static final String TAG = "EditProfileFragment";
    private HomeActivity mParent;
    private View rootView;

    private EditText edt_first_name;
    private TextView tv_icon_first_name;

    private EditText edt_last_name;
    private TextView tv_icon_last_name;

    private EditText edt_company_name;
    private TextView tv_icon_company_name;

    private EditText edt_business_email_id;
    private TextView tv_business_email_id;

    private EditText edt_personal_mail_id;
    private TextView tv_personal_mail_id;

    private EditText edt_contact;
    private TextView tv_contact;

    private EditText edt_alternate;
    private TextView tv_alternate;

    private EditText edt_location_icon;
    private TextView tv_location_icon;

    private TextView tv_interested_location;
    private LinearLayout ll_hyd;
    private CheckBox cb_hyd;
    private TextView tv_hyd;

    private LinearLayout ll_chennai;
    private CheckBox cb_chennai;
    private TextView tv_chennai;

    private LinearLayout ll_bangalore;
    private CheckBox cb_bangalore;
    private TextView tv_bangalore;

    private LinearLayout ll_pune;
    private CheckBox cb_pune;
    private TextView tv_pune;

    private TextView tv_edit;

    private ImageView img_user_image;

    private Button btn_save;

    private Typeface mTypefaceRobot;
    private Typeface mTypefaceFontAwesome;

    private boolean mImageChanged = false;
    private String mChangedPicString = "";
    private String mImgpath;
    private String mEncodedImage;
    private String mImgpathExtenstion;

    private static IUpdateSelectedPic iUpdateSelectedPic;

    public static IUpdateSelectedPic getInstance() {
        return iUpdateSelectedPic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (HomeActivity) getActivity();
        iUpdateSelectedPic = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParent.getSupportActionBar().setTitle(Utility.setHeaderTypeface(mParent,
                Utility.getResourcesString(getActivity(), R.string.edit_profile)));
        if (rootView != null) {
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {
        edt_first_name = (EditText) rootView.findViewById(R.id.edt_first_name);
        tv_icon_first_name = (TextView) rootView.findViewById(R.id.tv_icon_first_name);
        edt_last_name = (EditText) rootView.findViewById(R.id.edt_last_name);
        tv_icon_last_name = (TextView) rootView.findViewById(R.id.tv_icon_last_name);
        edt_company_name = (EditText) rootView.findViewById(R.id.edt_company_name);
        tv_icon_company_name = (TextView) rootView.findViewById(R.id.tv_icon_company_name);
        edt_business_email_id = (EditText) rootView.findViewById(R.id.edt_business_email_id);
        tv_business_email_id = (TextView) rootView.findViewById(R.id.tv_business_email_id);
        edt_personal_mail_id = (EditText) rootView.findViewById(R.id.edt_personal_mail_id);
        tv_personal_mail_id = (TextView) rootView.findViewById(R.id.tv_personal_mail_id);

        tv_edit = (TextView) rootView.findViewById(R.id.tv_edit);

        edt_contact = (EditText) rootView.findViewById(R.id.edt_contact);
        tv_contact = (TextView) rootView.findViewById(R.id.tv_contact);

        edt_alternate = (EditText) rootView.findViewById(R.id.edt_alternate);
        tv_alternate = (TextView) rootView.findViewById(R.id.tv_alternate);

        edt_location_icon = (EditText) rootView.findViewById(R.id.edt_location_icon);
        tv_location_icon = (TextView) rootView.findViewById(R.id.tv_location_icon);

        tv_hyd = (TextView) rootView.findViewById(R.id.tv_hyd);
        tv_chennai = (TextView) rootView.findViewById(R.id.tv_chennai);
        tv_pune = (TextView) rootView.findViewById(R.id.tv_pune);
        tv_bangalore = (TextView) rootView.findViewById(R.id.tv_bangalore);

        cb_hyd = (CheckBox) rootView.findViewById(R.id.cb_hyd);
        cb_chennai = (CheckBox) rootView.findViewById(R.id.cb_chennai);
        cb_bangalore = (CheckBox) rootView.findViewById(R.id.cb_bangalore);
        cb_pune = (CheckBox) rootView.findViewById(R.id.cb_pune);

        tv_interested_location = (TextView) rootView.findViewById(R.id.tv_interested_location);
        btn_save = (Button) rootView.findViewById(R.id.btn_save);

        img_user_image = (ImageView) rootView.findViewById(R.id.img_user_image);

        setTypeface();
    }

    private void setTypeface() {
        mTypefaceRobot = Utility.setTypeFaceRobotoRegular(getActivity());
        mTypefaceFontAwesome = Utility.setTypeFace_fontawesome(getActivity());

        edt_first_name.setTypeface(mTypefaceRobot);
        tv_icon_first_name.setTypeface(mTypefaceFontAwesome);
        edt_last_name.setTypeface(mTypefaceRobot);
        tv_icon_last_name.setTypeface(mTypefaceFontAwesome);
        edt_company_name.setTypeface(mTypefaceRobot);
        tv_icon_company_name.setTypeface(mTypefaceFontAwesome);
        edt_business_email_id.setTypeface(mTypefaceRobot);
        tv_business_email_id.setTypeface(mTypefaceFontAwesome);
        edt_personal_mail_id.setTypeface(mTypefaceRobot);
        tv_personal_mail_id.setTypeface(mTypefaceFontAwesome);

        edt_contact.setTypeface(mTypefaceRobot);
        tv_contact.setTypeface(mTypefaceFontAwesome);

        edt_alternate.setTypeface(mTypefaceRobot);
        tv_alternate.setTypeface(mTypefaceFontAwesome);

        edt_location_icon.setTypeface(mTypefaceRobot);
        tv_location_icon.setTypeface(mTypefaceFontAwesome);

        tv_edit.setTypeface(mTypefaceFontAwesome);

        tv_hyd.setTypeface(mTypefaceRobot);
        tv_chennai.setTypeface(mTypefaceRobot);
        tv_pune.setTypeface(mTypefaceRobot);
        tv_bangalore.setTypeface(mTypefaceRobot);

        cb_hyd.setTypeface(mTypefaceRobot);
        cb_chennai.setTypeface(mTypefaceRobot);
        cb_bangalore.setTypeface(mTypefaceRobot);
        cb_pune.setTypeface(mTypefaceRobot);

        tv_interested_location.setTypeface(mTypefaceRobot);
        btn_save.setTypeface(mTypefaceRobot);

        getPrePopulateData();
    }

    private void getPrePopulateData() {
        edt_first_name.setText(Utility.capitalizeFirstLetter(Utility.getSharedPrefStringData(getActivity(), Constants.FIRST_NAME)));
        edt_last_name.setText(Utility.capitalizeFirstLetter(Utility.getSharedPrefStringData(getActivity(), Constants.LAST_NAME)));
        edt_company_name.setText(Utility.capitalizeFirstLetter(Utility.getSharedPrefStringData(getActivity(), Constants.COMPANY_NAME)));
        edt_business_email_id.setText(Utility.getSharedPrefStringData(getActivity(), Constants.BUSINESS_MAIL_ID));
        edt_personal_mail_id.setText(Utility.getSharedPrefStringData(getActivity(), Constants.PERSONAL_MAIL_ID));
        edt_contact.setText(Utility.getSharedPrefStringData(getActivity(), Constants.CONTACT));
        edt_alternate.setText(Utility.getSharedPrefStringData(getActivity(), Constants.ALTERNATE));
        edt_location_icon.setText(Utility.capitalizeFirstLetter(Utility.getSharedPrefStringData(getActivity(), Constants.CURRENT_LOCATION)));

        if (!Utility.isValueNullOrEmpty(Utility.getSharedPrefStringData(getActivity(), Constants.PROFILE_PIC)))
            Picasso.with(getActivity()).load(Utility.getSharedPrefStringData(getActivity(), Constants.PROFILE_PIC)).
                    placeholder(Utility.getDrawable(getActivity(), R.drawable.avatar_image))
                    .transform(new CircleTransform()).into(img_user_image);

        if (Utility.getSharedPrefStringData(getActivity(), Constants.INTERESTED_LOCATION).equalsIgnoreCase("Hyderabad")) {
            cb_hyd.setChecked(true);
        } else if (Utility.getSharedPrefStringData(getActivity(), Constants.INTERESTED_LOCATION).equalsIgnoreCase("Pune")) {
            cb_pune.setChecked(true);
        } else if (Utility.getSharedPrefStringData(getActivity(), Constants.INTERESTED_LOCATION).equalsIgnoreCase("Chennai")) {
            cb_bangalore.setChecked(true);
        } else if (Utility.getSharedPrefStringData(getActivity(), Constants.INTERESTED_LOCATION).equalsIgnoreCase("Bangalore")) {
            cb_chennai.setChecked(true);
        }

        cb_hyd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_pune.setChecked(false);
                    cb_chennai.setChecked(false);
                    cb_bangalore.setChecked(false);
                    //cb_hyd.setChecked(true);
                }
            }
        });

        cb_pune.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_hyd.setChecked(false);
                    cb_chennai.setChecked(false);
                    cb_bangalore.setChecked(false);
                }
            }
        });

        cb_bangalore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_hyd.setChecked(false);
                    cb_chennai.setChecked(false);
                    cb_pune.setChecked(false);
                }
            }
        });

        cb_chennai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_bangalore.setChecked(false);
                    cb_hyd.setChecked(false);
                    cb_pune.setChecked(false);
                }
            }
        });

        btn_save.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                if (isValidFields()) {
                    sendEditData();
                }
                break;
            case R.id.tv_edit:
                showSelectPhotoDialog();
                break;
        }
    }

    private void sendEditData() {
        if (Utility.isNetworkAvailable(getActivity())) {
            LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
            paramMap.put("user_id", Utility.getSharedPrefStringData(getActivity(), Constants.USER_ID));
            paramMap.put("first_name", edt_first_name.getText().toString());
            paramMap.put("last_name", edt_last_name.getText().toString());
            paramMap.put("company_name", edt_company_name.getText().toString());
            if (mImageChanged) {
                paramMap.put("profile_pic", mEncodedImage);
                paramMap.put("file_extension", mImgpathExtenstion);
            }
            paramMap.put("business_email_id", edt_business_email_id.getText().toString());
            //paramMap.put("personal_email_id", edt_personal_mail.getText().toString());
            paramMap.put("contact_number", edt_contact.getText().toString());
            paramMap.put("alternate_contact_number", edt_alternate.getText().toString());
            paramMap.put("current_location", edt_location_icon.getText().toString());

            if (cb_hyd.isChecked() == true) {
                paramMap.put("interested_location", "Hyderabad");
            } else if (cb_pune.isChecked() == true) {
                paramMap.put("interested_location", "Pune");
            } else if (cb_chennai.isChecked() == true) {
                paramMap.put("interested_location", "Chennai");
            } else if (cb_bangalore.isChecked() == true) {
                paramMap.put("interested_location", "Bangalore");
            }

            SignUpParser mParser = new SignUpParser();
            ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                    R.string.please_wait), true,
                    APIConstants.EDIT_PROFILE, paramMap,
                    APIConstants.REQUEST_TYPE.POST, this, mParser);
            Utility.execute(serverIntractorAsync);

        } else {
            Utility.showSettingDialog(
                    getActivity(),
                    getActivity().getResources().getString(
                            R.string.no_internet_msg),
                    getActivity().getResources().getString(
                            R.string.no_internet_title),
                    Utility.NO_INTERNET_CONNECTION).show();
        }
    }

    private boolean isValidFields() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(edt_first_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_first_name, "Please enter first name");
            edt_first_name.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_last_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_last_name, "Please enter last name");
            edt_last_name.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_company_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_company_name, "Please enter company name");
            edt_company_name.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_business_email_id.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_business_email_id, "Please enter business mail");
            edt_business_email_id.requestFocus();
        } else if (!edt_business_email_id.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            Utility.setSnackBarEnglish(mParent, edt_business_email_id, "Please enter valid business mail");
            edt_business_email_id.requestFocus();
        } /*else if (Utility.isValueNullOrEmpty(edt_personal_mail.getText().toString().trim())) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_personal_mail, "Please enter personal mail");
            edt_personal_mail.requestFocus();
        } else if (!edt_personal_mail.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            Utility.setSnackBarEnglish(SignupActivity.this, edt_personal_mail, "Please enter valid business mail");
            edt_personal_mail.requestFocus();
        } */ else if (Utility.isValueNullOrEmpty(edt_contact.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_contact, "Please enter contact number");
            edt_contact.requestFocus();
        } else if (edt_contact.getText().toString().trim().length() != 10) {
            Utility.setSnackBarEnglish(mParent, edt_contact, "Contact number must me 10 characteristics");
            edt_contact.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_alternate.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_alternate, "Please enter alternate contact number");
            edt_alternate.requestFocus();
        } else if (edt_alternate.getText().toString().trim().length() != 10) {
            Utility.setSnackBarEnglish(mParent, edt_alternate, "Alternate contact number must me 10 characteristics");
            edt_alternate.requestFocus();
        } else if (Utility.isValueNullOrEmpty(edt_location_icon.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_location_icon, "Please enter current location");
            edt_location_icon.requestFocus();
        } else if (cb_hyd.isChecked() != true && cb_pune.isChecked() != true && cb_chennai.isChecked() != true && cb_bangalore.isChecked() != true) {
            Utility.setSnackBarEnglish(mParent, cb_hyd, "please select interested location");
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
                    saveUpdatedData(mSignUpModel);
                }
            }
        }
    }

    private void saveUpdatedData(SignUpModel mSignUpModel) {
        Utility.setSharedPrefStringData(mParent, Constants.FIRST_NAME, edt_first_name.getText().toString());
        Utility.setSharedPrefStringData(mParent, Constants.LAST_NAME, edt_last_name.getText().toString());
        Utility.setSharedPrefStringData(mParent, Constants.COMPANY_NAME, edt_company_name.getText().toString());
        HomeActivity.txt_name.setText("" + Utility.capitalizeFirstLetter(edt_first_name.getText().toString()) + " "
                + Utility.capitalizeFirstLetter(edt_last_name.getText().toString()));
        HomeActivity.txt_user_designation.setText("" + Utility.capitalizeFirstLetter(edt_company_name.getText().toString()));
        Utility.setSharedPrefStringData(mParent, Constants.BUSINESS_MAIL_ID, edt_business_email_id.getText().toString());
        //Utility.setSharedPrefStringData(mParent, Constants.PERSONAL_MAIL_ID, edt_personal_mail.getText().toString());
        Utility.setSharedPrefStringData(mParent, Constants.CONTACT, edt_contact.getText().toString());
        Utility.setSharedPrefStringData(mParent, Constants.ALTERNATE, edt_alternate.getText().toString());
        Utility.setSharedPrefStringData(mParent, Constants.CURRENT_LOCATION, edt_location_icon.getText().toString());
        Utility.setSharedPrefStringData(mParent, Constants.INTERESTED_LOCATION, mSignUpModel.getInterested_location());

        Utility.showToastMessage(getActivity(), "Profile Edited Successfully");
        mParent.onBackPressed();
    }

    public void showSelectPhotoDialog() {
        final Dialog dialogShare = new Dialog(getActivity());
        dialogShare.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogShare.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogShare.setContentView(R.layout.dialog_choose_photo_popup);
        dialogShare.getWindow().setGravity(Gravity.BOTTOM);
        dialogShare.setCanceledOnTouchOutside(true);
        dialogShare.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialogShare.getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));


        TextView txt_shr_popup_title = (TextView) dialogShare.findViewById(R.id.txt_shr_popup_title);
        txt_shr_popup_title.setTypeface(Utility.setTypeFaceRobotoBold(getActivity()));

        /*CAMERA*/
        LinearLayout llCamera = (LinearLayout) dialogShare.findViewById(R.id.ll_take_photo);
        final TextView tvCamera = (TextView) dialogShare.findViewById(R.id.tv_take_photo);
        tvCamera.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mParent.startActivityForResult(intent, Constants.FROM_EDIT_CAMERA_ID);
                dialogShare.dismiss();
            }
        });

        /*GALLERY*/
        LinearLayout llGallery = (LinearLayout) dialogShare.findViewById(R.id.ll_gallery);
        TextView tvGallery = (TextView) dialogShare.findViewById(R.id.tv_choose_gallery);
        tvGallery.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        llGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mParent.startActivityForResult(i, Constants.FROM_EDIT_GALLERY_ID);
                dialogShare.dismiss();
            }
        });

          /*REMOVE*/
        LinearLayout llRemove = (LinearLayout) dialogShare.findViewById(R.id.ll_remove);
        TextView tvRemove = (TextView) dialogShare.findViewById(R.id.tv_remove);
        tvRemove.setTypeface(Utility.setTypeFaceRobotoRegular(getActivity()));
        llRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShare.dismiss();
            }
        });
        dialogShare.show();
    }

    @Override
    public void updateProfilePic(String path) {
        mImageChanged = true;
        mChangedPicString = path;

        Bitmap bitmapImage = BitmapFactory.decodeFile(path);
        int nh = (int) (bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        scaled.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        mEncodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        mImgpathExtenstion = "";
        int i = path.lastIndexOf('.');
        if (i > 0) {
            mImgpathExtenstion = path.substring(i + 1);
        }
    }

    @Override
    public void updateDoc(File path) {

    }

    @Override
    public void updatePdf(File path) {

    }
}
