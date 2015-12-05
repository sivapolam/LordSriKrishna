package com.sivapolam.jesusthelord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.logging.Handler;

/**
 * Created by pnaganjane001 on 12/11/15.
 */
public class SplashActivity extends Activity {

    private static final long SPLASH_SLEEP_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        pauseAndLoadActivity();
    }

    private void pauseAndLoadActivity() {
        Thread splashThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(SPLASH_SLEEP_TIME);
                    startActivity(new Intent(SplashActivity.this, Home.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();
    }
}
