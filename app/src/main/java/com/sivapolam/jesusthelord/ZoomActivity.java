package com.sivapolam.jesusthelord;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.sivapolam.jesusthelord.adapters.GalleryFullScreenAdapter;

/**
 * Created by pnaganjane001 on 14/11/15.
 */
public class ZoomActivity extends ActionBarActivity implements Constants{

    ViewPager viewPager;
    GalleryFullScreenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_view_fullscreen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.pager);

        int position = getIntent().getIntExtra(ARG_POSITION_GALLERY, 0);

        adapter = new GalleryFullScreenAdapter(this,
                GalleryFragment.galleryImages);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
