package testapp.testing.com.mvppro.locationTaker;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import testapp.testing.com.mvppro.R;

public class LocationActivity extends Activity {
    public static String str_receiver = "servicetutorial.service.receiver";

    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        registerReceiver(broadcastReceiver, new IntentFilter(str_receiver));

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);


    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(LocationActivity.this, "Broadcast receinved", Toast.LENGTH_SHORT).show();

            latitude = Double.valueOf(intent.getStringExtra("latutide"));
            longitude = Double.valueOf(intent.getStringExtra("longitude"));


        }
    };

    public void startLoc(View view) {
        startService(new Intent(this, LocationService.class));

        startAlarm();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(LocationActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


void startAlarm()
{
    final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    try {

        Intent i = new Intent(getApplicationContext(),
                serviceReceiver.class);
        PendingIntent ServiceManagementIntent = PendingIntent
                .getBroadcast(getApplicationContext(), 1, i, 0);

        /*
         * alarmManager.set(AlarmManager.RTC_WAKEUP,
         * System.currentTimeMillis(), ServiceManagementIntent);
         */

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), (1000 * 60 * 48 * 60),
                ServiceManagementIntent);
    }catch (Exception e)
    {
        e.printStackTrace();
    }
}




void startIntAlrSrv()
{
    // Construct an intent that will execute the AlarmReceiver
    Intent intent = new Intent(getApplicationContext(), MyIntService.class);
    // Create a PendingIntent to be triggered when the alarm goes off
    final PendingIntent pIntent = PendingIntent.getBroadcast(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    // Setup periodic alarm every every half hour from this point onwards
    long firstMillis = System.currentTimeMillis(); // alarm is set right away
    AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
    // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
    // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
    alarm.setRepeating(AlarmManager.RTC_WAKEUP, firstMillis, (long) (1000 * 60), pIntent);


}

    public void startInts(View view)
    {
        startIntAlrSrv();
    }

    public void startsERV(View view)
    {
        startService(new Intent(this,MyServiceLong.class));
    }

    void startServ1() {
        /**
         * Starting Service Class to start Services
         */

        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        try {

            Intent i = new Intent(getApplicationContext(),
                    MyIntService.class);
            PendingIntent ServiceManagementIntent = PendingIntent
                    .getBroadcast(getApplicationContext(),
                            1, i, 0);

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    ServiceManagementIntent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void startsERV1(View view)
    {
        startServ1();
    }

    public void startsERVBAsync(View view)
    {

        new MyAsync().execute();
    }

    public void startalarmNow(View view)
    {
        startAlarmNow();
    }

    public void startTimerService(View view)
    {
        new TimerServiceStarterAsync().execute();
    }


    class MyAsync extends AsyncTask
    {


        @Override
        protected Object doInBackground(Object[] objects) {

            Intent gpsTrackService = new Intent(LocationActivity.this, MyServiceLong.class);
            startService(gpsTrackService);

            return null;
        }
    }

    class TimerServiceStarterAsync extends AsyncTask
    {


        @Override
        protected Object doInBackground(Object[] objects) {

            Intent gpsTrackService = new Intent(LocationActivity.this,
                    GPSTrackerService.class);
            startService(gpsTrackService);

            return null;
        }
    }

    void startAlarmNow()
    {
        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        try {

            Intent i = new Intent(getApplicationContext(),
                    serviceReceiver.class);
            getApplicationContext().startService(i);
            //karthik
                            /*PendingIntent ServiceManagementIntent = PendingIntent
                                    .getBroadcast(getApplicationContext(), 1,
											i, 0);

							alarmManager.set(AlarmManager.RTC_WAKEUP,
									System.currentTimeMillis(),
									ServiceManagementIntent);
*/
        } catch (Exception e) {
            Log.i("dff", "Exception : " + e);
        }
    }




}
