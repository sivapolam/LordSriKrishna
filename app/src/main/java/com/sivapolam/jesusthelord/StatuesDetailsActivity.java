package com.sivapolam.jesusthelord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sivapolam.jesusthelord.adapters.GalleryFullScreenAdapter;
import com.sivapolam.jesusthelord.adapters.StatuesFullScreenAdapter;
import com.sivapolam.jesusthelord.util.PinchZoomView;

/**
 * Created by pnaganjane001 on 12/11/15.
 */
public class StatuesDetailsActivity extends ActionBarActivity implements Constants{

    int[] statuesArray = new int[]{
            R.drawable.statue1,R.drawable.statue2,R.drawable.statue3,R.drawable.statue4,R.drawable.statue5,
            R.drawable.statue6,R.drawable.statue7,R.drawable.statue8,R.drawable.statue9,R.drawable.statue10,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_view_fullscreen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        int position = getIntent().getIntExtra(HISTORY_SELECTED_POSITION, 0);

        StatuesFullScreenAdapter adapter = new StatuesFullScreenAdapter(this,
                statuesArray);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);

        /*TextView statueTitle = (TextView)findViewById(R.id.statue_title);
        TextView statueDescription = (TextView)findViewById(R.id.statue_description);
        PinchZoomView statueImage = (PinchZoomView)findViewById(R.id.statue_image);
        ImageView detailsImage = (ImageView)findViewById(R.id.details_image);
        final ScrollView descriptionLayout = (ScrollView)findViewById(R.id.layout_desc);

        detailsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionLayout.setVisibility(View.VISIBLE);
            }
        });

        if (getResources().getStringArray(R.array.famous_statues_descriptions_array).length > 0
                && getResources().getStringArray(R.array.famous_statues_descriptions_array).length > position){
            statueTitle.setText(getResources().getStringArray(R.array.famous_statues_places_array)[position]);
            statueDescription.setText(getResources().getStringArray(R.array.famous_statues_descriptions_array)[position]);
            statueImage.setBackgroundResource(statuesArray[position]);
            getSupportActionBar().setTitle("Famous Statues");
        } else {
            statueTitle.setText("No Data Found");
            statueDescription.setText("No Data Found");
        }*/

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
