package com.sivapolam.jesusthelord.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sivapolam.jesusthelord.R;
import com.sivapolam.jesusthelord.util.PinchZoomView;

/**
 * Created by pnaganjane001 on 14/11/15.
 */
public class StatuesFullScreenAdapter extends PagerAdapter {
    private Activity _activity;
    private int[] _imagePaths;
    private LayoutInflater inflater;
    private boolean isDescriptionOpened;

    // constructor
    public StatuesFullScreenAdapter(Activity activity,
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
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.staues_details, container,
                false);


        TextView statueTitle = (TextView)viewLayout.findViewById(R.id.statue_title);
        TextView statueDescription = (TextView)viewLayout.findViewById(R.id.statue_description);
        ImageView statueImage = (ImageView)viewLayout.findViewById(R.id.statue_image);
        ImageView detailsImage = (ImageView)viewLayout.findViewById(R.id.details_image);
        final ScrollView descriptionLayout = (ScrollView)viewLayout.findViewById(R.id.layout_desc);

        statueImage.setImageResource(_imagePaths[position]);
        statueTitle.setText(this._activity.getResources().getStringArray(R.array.famous_statues_places_array)[position]);
        statueDescription.setText(this._activity.getResources().getStringArray(R.array.famous_statues_descriptions_array)[position]);

        descriptionLayout.setVisibility(View.GONE);
        isDescriptionOpened = false;

        detailsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDescriptionOpened){
                    descriptionLayout.setVisibility(View.VISIBLE);
                    isDescriptionOpened = true;
                }else{
                    descriptionLayout.setVisibility(View.GONE);
                    isDescriptionOpened = false;
                }
            }
        });

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
