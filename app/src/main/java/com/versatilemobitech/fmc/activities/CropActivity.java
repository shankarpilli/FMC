package com.versatilemobitech.fmc.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.isseiaoki.simplecropview.CropImageView;
import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.fragments.EditProfileFragment;
import com.versatilemobitech.fmc.utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Shankar on 11/21/2016.
 */

public class CropActivity extends BaseActivity implements View.OnClickListener {

    private String mForm = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_activity);
        setUI();
    }


    @SuppressLint("SetTextI18n")
    private void setUI() {

        Bundle bundle = getIntent().getExtras();
        final String imagePath = bundle.getString("image_path");
        mForm = bundle.getString("from");
        /*TITLE*/
        TextView txt_registration_title = (TextView) findViewById(R.id.txt_registration_title);
        assert txt_registration_title != null;
        txt_registration_title.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        txt_registration_title.setText("Crop Picture");


         /*BACK*/
        TextView txt_registration_back = (TextView) findViewById(R.id.txt_registration_back);
        assert txt_registration_back != null;
        txt_registration_back.setTypeface(Utility.setTypeFace_fontawesome(this));
        txt_registration_back.setOnClickListener(this);


        final CropImageView mCropView = (CropImageView) findViewById(R.id.iv_crop_view);
        assert mCropView != null;
        mCropView.setCropMode(CropImageView.CropMode.CIRCLE_SQUARE);
        Bitmap mBitmap = getRotatedBitmap(0, imagePath);
        mCropView.setImageBitmap(mBitmap);



         /*BUTTON CROP*/
        Button btn_crop = (Button) findViewById(R.id.btn_crop);
        assert btn_crop != null;
        btn_crop.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        btn_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = mCropView.getCroppedBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                String temp = Base64.encodeToString(b, Base64.DEFAULT);

                if (mForm.equalsIgnoreCase("RegistrationActivity")) {
                    SignupActivity.getInstance().updateProfilePic(temp, imagePath);
                } else if (mForm.equalsIgnoreCase("EditProfileFragment")) {
                    EditProfileFragment.getInstance().updateProfilePic(temp, imagePath, bitmap);
                }
                finish();
            }
        });

        /*BUTTON CANCEL*/
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        assert btn_cancel != null;
        btn_cancel.setTypeface(Utility.setTypeFaceRobotoRegular(this));
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }


    public static Bitmap getRotatedBitmap(int rotation, String mPath) {
        File f = new File(mPath);
        Bitmap mBitMap = BitmapFactory.decodeFile(f.getAbsolutePath());

        int width = mBitMap.getWidth();
        int height = mBitMap.getHeight();

        /*ULTRA HEIGHT RESOLUTION IMAGE */
        if (width > 1920 && height > 1080) {

            if (width > height)
                mBitMap = Bitmap.createScaledBitmap(mBitMap, 1920, 1080, false);
            else
                mBitMap = Bitmap.createScaledBitmap(mBitMap, 1080, 1920, false);

            Bitmap oldBitmap = mBitMap;
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            mBitMap = Bitmap.createBitmap(oldBitmap, 0, 0, oldBitmap.getWidth(), oldBitmap.getHeight(), matrix, false);
        } else if (rotation != 0) {
            Bitmap oldBitmap = mBitMap;
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            mBitMap = Bitmap.createBitmap(oldBitmap, 0, 0, oldBitmap.getWidth(), oldBitmap.getHeight(), matrix, false);
            oldBitmap.recycle();
        }
        return mBitMap;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_registration_back:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    private Bitmap getBitmap(String mPath) {
        File f = new File(mPath);
        return BitmapFactory.decodeFile(f.getAbsolutePath());
    }

}