package com.sivapolam.jesusthelord.adapters;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sivapolam.jesusthelord.R;
import com.sivapolam.jesusthelord.util.Utility;

import java.io.IOException;

/**
 * Created by pnaganjane001 on 14/11/15.
 */
public class GalleryFullScreenAdapter extends PagerAdapter {
    private Activity _activity;
    private int[] _imagePaths;
    private LayoutInflater inflater;

    // constructor
    public GalleryFullScreenAdapter(Activity activity,
                                  int[] imagePaths) {
        this._activity = activity;
        this._imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return this._imagePaths.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.zoom_layout, container,
                false);

        ImageView imgDisplay = (ImageView) viewLayout.findViewById(R.id.zoom_image);

        imgDisplay.setImageResource(_imagePaths[position]);

        ((ViewPager) container).addView(viewLayout);

        imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.setWallpaper(_activity.getApplicationContext(), _imagePaths[position]);
            }
        });

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
