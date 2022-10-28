package com.example.alligatorstours.beacons;

import android.app.Application;
import android.util.Log;

public class GimbalApp extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("-----", "GimbalApplication created");

        GimbalIntegration.init(this).onCreate();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        GimbalIntegration.init(this).onTerminate();
    }
}
