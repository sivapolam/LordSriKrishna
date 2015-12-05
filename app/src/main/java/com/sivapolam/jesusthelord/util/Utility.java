package com.sivapolam.jesusthelord.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by pnaganjane001 on 21/11/15.
 */
public class Utility {
    public static void setWallpaper(Context context, int imageResource){
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(context);
        try {
            myWallpaperManager.setResource(imageResource);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void shareApp(Context context){
        Intent i=new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Share test");
        i.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.facebook.katana");
        context.startActivity(Intent.createChooser(i, "Share via"));
    }

    public static void feedback(Context context){
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana"));
        context.startActivity(Intent.createChooser(i, "Share via"));
    }

}
