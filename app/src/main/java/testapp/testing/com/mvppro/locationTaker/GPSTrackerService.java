package testapp.testing.com.mvppro.locationTaker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Created by A10436 on 26/10/15.
 */
public class GPSTrackerService extends Service implements LocationListener, ConnectionCallbacks, OnConnectionFailedListener,
        ResultCallback<Status> {
    // flag for GPS status
    private GoogleApiClient gClient;
    private PendingIntent mGeofencePendingIntent;
    Location location;
    protected LocationManager locationManager;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; //100 mts
    private static final int RADIUS = 200;

    private static long MIN_TIME_BW_UPDATES;// = 0;//10000;
    SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
        Locale.US);
    boolean START = true;

   // NischintDB db = new NischintDB(this);

    public final IBinder mBinder = new LocalBinder();
    private static boolean firstConnect = true;


   // String url = com.pagesolutions.nischint.util.Constants.BASE_URL + "/setServiceLogs";

    List<JSONObject> gpsLogValueslist = new ArrayList<JSONObject>();


    public class LocalBinder extends Binder {
        GPSTrackerService getService() {
            // Return this instance of LocalService so clients can call public methods
            return GPSTrackerService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Don't start Service if any permission is not granted/enabled.
       /* if (!Utils.checkAllTargetPermissions(getApplicationContext())) {
            // All Permissions Not Granted
            stopSelf();
            return START_NOT_STICKY;
        }*/
        if (gClient == null) {
            connectGoogleAPI();
        } else if (gClient.isConnected() && location != null) {
            if (!createLocalFence(location, RADIUS)) {
                //creation of local fence failed - stop the service
                //TODO - check for  alternatives to handle this event
                Log.i("GPSTrackerService", "Could not start the GPSTrackerService as local fence could not be created");
                //  this.stopSelf();

            } else {
                //Fence created successfully
                //this.stopSelf();
            }
        } else {
            //connectGoogleAPI();
        }
        // Don't start Service if any permission is not granted/enabled.
        /*if (!Utils.checkAllTargetPermissions(getApplicationContext())) {
            // All Permissions Not Granted
            stopSelf();
            return START_NOT_STICKY;
        }*/

        return START_STICKY;
    }


    @Override

    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (gClient != null) {
            gClient.disconnect();
        }

        if (locationManager != null) {
            //    locationManager.removeUpdates(this);
        }

    }


    public boolean addLocalGeoFence(GoogleApiClient gClient, Location loc, float radius) {
        boolean status = false;
        Geofence geofence = new Geofence.Builder()

            .setRequestId("LOCALFENCE")
            .setCircularRegion(
                loc.getLatitude(),
                loc.getLongitude(),
                radius
            )
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_EXIT)
            .build();

        if (geofence != null) {

           /* try {
                LocationServices.GeofencingApi.addGeofences(
                    gClient,
                    getGeofencingRequest(geofence),
                    getGeofencePendingIntent()
                ).setResultCallback(this);

                status = true;
                Log.i("GPSTrackerService", "Added the local fence.");

            } catch (Exception ex) {
                status = false;
                Log.i("GPSTrackerService", "Failed adding the local fence.");

            }*/
        }
        return status;
    }




    @Override
    public void onLocationChanged(Location location) {

        //do nothing
        if (!firstConnect)
            return;

        try {

            if (location == null) {
                Log.i("GPSTrackerService", "Could not start the GPSTrackerService as current location could not be fetched.");

            } else {

                firstConnect = false; //got connected for the first time .
                if (START) {
                    //uploadGPSData(location, RADIUS);
                    START = false;
                }
                Log.i("GPSTrackerService", "Fetched current location.");

            }
        } catch (Exception ex) {
            Log.i("GPSTrackerService", "Could not start the GPSTrackerService as current location could not be fetched.");

        }

        if (location != null) {
            if (!createLocalFence(location, RADIUS)) {
                //creation of local fence failed - stop the service
                //TODO - check for  alternatives to handle this event
                Log.i("GPSTrackerService", "Could not start the GPSTrackerService as local fence could not be created");
                //  this.stopSelf();

            } else {
                //Fence created successfully
                //stop the service
                //this.stopSelf();
                Log.i("GPSTrackerService", "Service started successfully.");

            }
        } else {
            //since the location could not be fetched lets stop the service.
            Log.i("GPSTrackerService", "Stopping the service as location could not be fetched.");
            //  this.stopSelf(); //do not stop the service . Listen to location manager events .
        }

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("GPSTrackerService", "Google API Client connection connected");
        firstConnect = true;
        LocationRequest mLocationRequest = new LocationRequest();
        //        mLocationRequest.setInterval(300000); //5 minutes
        //        mLocationRequest.setFastestInterval(60000); //fastest interval 1 minute
        mLocationRequest.setInterval(30000); //For 30 Seconds
        mLocationRequest.setFastestInterval(10000); //for 10 Seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(gClient, mLocationRequest, this);
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i("GPSTrackerService", "Google API Client connection suspended");
        // nothing is needed to be stopped here as no UI is involved

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("GPSTrackerService", "Google API Client connection failed");
    }


    private GeofencingRequest getGeofencingRequest(Geofence geofence) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofence(geofence);
        return builder.build();
    }

    @Override
    public void onResult(Status status) {

    }


    public boolean createLocalFence(Location mLocation, float radius) {
        boolean status = false;
        status = addLocalGeoFence(gClient, mLocation, radius);
        return status;
    }


    private boolean connectGoogleAPI() {
        boolean status = false;

        try {
            gClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

            gClient.connect();


            status = true;

        } catch (Exception ex) {
            String error;
            error = "Could not connect to google play services . Reason : " + ex.getMessage();
            Log.i("connectGoogleAPI", error);
        }

        return status;

    }



}




