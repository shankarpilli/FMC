package com.versatilemobitech.fmc.adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.versatilemobitech.fmc.R;
import com.versatilemobitech.fmc.customviews.TouchImageView;
import com.versatilemobitech.fmc.models.CSRViewModel;
import com.versatilemobitech.fmc.utility.Utility;

import java.util.List;

/**
 * Created by shankar on 1/20/17.
 */

public class CSRImageViewPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<CSRViewModel> mImageGallerySerializableList;
    private Context parent;


    public CSRImageViewPagerAdapter(Context parent, List<CSRViewModel> stringList) {
        this.mImageGallerySerializableList = stringList;
        this.parent = parent;
        mLayoutInflater = (LayoutInflater) parent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(100, 170);
            mLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public int getCount() {
        return mImageGallerySerializableList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout;
        imageLayout = mLayoutInflater.inflate(R.layout.csr_view_pager_item,
                view, false);
        assert imageLayout != null;
        ImageView image = (ImageView) imageLayout.findViewById(R.id.image);
        image.setTag(mImageGallerySerializableList.get(position).getImage_path());
        TextView tv_date = (TextView) imageLayout.findViewById(R.id.tv_date);
        TextView tv_title = (TextView) imageLayout.findViewById(R.id.tv_title);
        TextView tv_description = (TextView) imageLayout.findViewById(R.id.tv_description);
        Utility.UILpicLoading(image, mImageGallerySerializableList.get(position).getImage_path(), null, R.drawable.folder_icon);
        tv_date.setText(mImageGallerySerializableList.get(position).getDatetime());
        tv_title.setText(Utility.capitalizeFirstLetter(mImageGallerySerializableList.get(position).getImage_title()));
        tv_description.setText(mImageGallerySerializableList.get(position).getImage_description());

        tv_date.setTypeface(Utility.setTypeFaceRobotoRegular(parent));
        tv_title.setTypeface(Utility.setTypeRobotoBoldRegular(parent));
        tv_description.setTypeface(Utility.setTypeFaceRobotoRegular(parent));

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = (String) view.getTag();
                showFitDialog(url, parent);
            }
        });
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    public void showFitDialog(String url, Context context) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_fitcenter);
        dialog.setCanceledOnTouchOutside(false);
        TouchImageView imageView = (TouchImageView) dialog.findViewById(R.id.imageView);
        //dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Utility.UILpicLoading(imageView, url, null, R.drawable.folder_icon);
        dialog.show();
        //imageView.
    }
}
