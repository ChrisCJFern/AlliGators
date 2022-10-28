package com.example.alligatorstours.beacons;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.Manifest;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gimbal.android.BeaconSighting;
import com.gimbal.android.Communication;
import com.gimbal.android.CommunicationListener;
import com.gimbal.android.CommunicationManager;
import com.gimbal.android.Gimbal;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.PlaceManager;
import com.gimbal.android.Visit;
import com.gimbal.android.BeaconEventListener;
import com.gimbal.android.BeaconManager;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.core.app.ActivityCompat;

public class GimbalIntegration {
    private static final String GIMBAL_APP_API_KEY = "232e8fda-2e7a-4f71-9a44-ddaae0f396ae";

    private static final int MAX_NUM_EVENTS = 100;

    private Application app;
    private Context     appContext;

    private PlaceEventListener placeEventListener;
    private CommunicationListener communicationListener;
    private BeaconEventListener beaconEventListener;

    private static GimbalIntegration instance;

    public static GimbalIntegration init(Application app) {
        if (instance == null) {
            instance = new GimbalIntegration(app);
        }
        return instance;
    }

    public static GimbalIntegration instance() {
        if (instance == null) {
            throw new IllegalStateException("Gimbal integration not initialized from Application");
        }
        return instance;
    }

    private GimbalIntegration(Application app) {
        this.app = app;
        this.appContext = app.getApplicationContext();
    }

    public void onCreate() {
        Gimbal.setApiKey(app, GIMBAL_APP_API_KEY);
        Log.i("Gimbal","API Key Set");

        //Setup beaconEventListener
        BeaconManager beaconManager = new BeaconManager();
        beaconEventListener = new BeaconEventListener(){
            // add BeaconEventListener method here
            @Override
            public void onBeaconSighting(BeaconSighting sighting) {
                Log.d("Gimbal", "Beacon " + sighting.getBeacon().getName() + " with a signal strength of " + sighting.getRSSI() + " has been sighted.");
            }
        };
        beaconManager.addListener(beaconEventListener);

        // Setup PlaceEventListener
        placeEventListener = new PlaceEventListener() {
            @Override
            public void onVisitStart(Visit visit) {
                Log.d("Gimbal","visited place" + visit.getPlace());
            }

            @Override
            public void onVisitStartWithDelay(Visit visit, int delayTimeInSeconds) {
                if (delayTimeInSeconds > 0) {
                }
            }

            @Override
            public void onVisitEnd(Visit visit) {
            }

            public void onBeaconSighting(BeaconSighting sighting, List<Visit> visits) {
                Log.d("Gimbal", "beacon sighted"); // This will be invoked when a beacon assigned to a place within a current visit is sighted.
            }
        };
        PlaceManager.getInstance().addListener(placeEventListener);

        // Setup CommunicationListener
        communicationListener = new CommunicationListener() {
            @Override
            public Notification.Builder prepareCommunicationForDisplay(Communication communication,
                                                                       Visit visit, int notificationId) {
                return null;
            }

            @Override
            public void onNotificationClicked(List<Communication> communications) {
                for (Communication communication : communications) {
                    if(communication != null) {
                    }
                }
            }
        };
        CommunicationManager.getInstance().addListener(communicationListener);
    }

    public void onTerminate() {
        PlaceManager.getInstance().removeListener(placeEventListener);
        CommunicationManager.getInstance().removeListener(communicationListener);
    }
}