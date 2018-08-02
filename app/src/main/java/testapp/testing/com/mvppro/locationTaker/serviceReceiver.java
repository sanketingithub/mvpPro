package testapp.testing.com.mvppro.locationTaker;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

public class serviceReceiver extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

      startAdvanceVersionServices();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class AdvanceServices implements Runnable {

        @Override
        public void run() {

            new MyAsync().execute();
        }
    }


    class MyAsync extends AsyncTask
    {


        @Override
        protected Object doInBackground(Object[] objects) {

            Intent gpsTrackService = new Intent(getApplicationContext(), MyServiceLong.class);
            startService(gpsTrackService);

            return null;
        }
    }

    private void startAdvanceVersionServices() {

       // Log.d(TAG, "***STARTING SERVIVES********" + "true");

        new Thread(new AdvanceServices()).start();

    }

}
