package com.sivapolam.jesusthelord.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sivapolam.jesusthelord.Constants;
import com.sivapolam.jesusthelord.R;
import com.sivapolam.jesusthelord.ZoomActivity;

/**
 * Created by pnaganjane001 on 14/11/15.
 */
public class GalleryAdapter extends BaseAdapter implements Constants{

    private Context context;
    int[] galleryImages;
    LayoutInflater inflater;

    public GalleryAdapter(Context context, int[] galleryImages) {
        this.context = context;
        this.galleryImages = galleryImages;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.galleryImages.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        final View rootView;
        rootView = inflater.inflate(R.layout.gallery_list_item, null);
        holder.galleryImage = (ImageView)rootView.findViewById(R.id.galley_image);
        holder.galleryImage.setImageResource(galleryImages[position]);

        rootView.setId(position);

        holder.galleryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zoomIntent = new Intent(context, ZoomActivity.class);
                zoomIntent.putExtra(ARG_POSITION_GALLERY, rootView.getId());
                zoomIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(zoomIntent);
            }
        });

//        holder.galleryImage.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                PopupMenu popupMenu = new PopupMenu(context,  holder.galleryImage);
////        popupMenu.setOnMenuItemClickListener(getActivity().getApplicationContext());
//                popupMenu.inflate(R.menu.home);
//                popupMenu.show();
//
//                return false;
//            }
//        });

        return rootView;
    }

    public class ViewHolder{
        ImageView galleryImage;
    }
}
