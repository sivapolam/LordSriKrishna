package com.sivapolam.jesusthelord;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sivapolam.jesusthelord.adapters.GalleryAdapter;

/**
 * Created by pnaganjane001 on 14/11/15.
 */
public class GalleryFragment extends Fragment implements Constants{

    GridView gridView;

    public static final int[] galleryImages = new int[]{R.drawable.splash_bg,
      R.drawable.jesus1, R.drawable.jesus2, R.drawable.jesus3, R.drawable.jesus4,R.drawable.jesus5,
      R.drawable.jesus7, R.drawable.jesus9,R.drawable.jesus10,
      R.drawable.jesus12, R.drawable.jesus14,R.drawable.jesus15,
      R.drawable.jesus16, R.drawable.jesus17, R.drawable.jesus19,R.drawable.jesus20,
      R.drawable.jesus21, R.drawable.jesus22, R.drawable.jesus23, R.drawable.jesus24,R.drawable.jesus25,
      R.drawable.jesus26, R.drawable.jesus27, R.drawable.jesus28, R.drawable.jesus29,R.drawable.jesus30,
      R.drawable.jesus31, R.drawable.jesus32, R.drawable.jesus33, R.drawable.jesus34,R.drawable.jesus35,
      R.drawable.jesus36, R.drawable.jesus37, R.drawable.jesus38, R.drawable.jesus40,
      R.drawable.jesus41, R.drawable.jesus42, R.drawable.jesus43, R.drawable.jesus44,R.drawable.jesus45
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View galleryView = inflater.inflate(R.layout.gallery, container,false);

        gridView = (GridView)galleryView.findViewById(R.id.gallery_grid);

        gridView.setAdapter(new GalleryAdapter(getActivity().getApplicationContext(), galleryImages));

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
//        popupMenu.setOnMenuItemClickListener(getActivity().getApplicationContext());
                popupMenu.getMenuInflater().inflate(R.menu.home, popupMenu.getMenu());
                popupMenu.show();
                return true;
            }
        });





        return galleryView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE, R.id.action_example, Menu.NONE, "Menu A");
        menu.add(Menu.NONE, R.id.action_settings, Menu.NONE, "Menu B");


    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Call"){
            Toast.makeText(getActivity().getApplicationContext(), "calling code", Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="SMS"){
            Toast.makeText(getActivity().getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Home) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
