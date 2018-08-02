package testapp.testing.com.mvppro.locationTaker;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyServiceLong extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startRep();

        return  START_STICKY;
    }

    void startRep()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startRep();
            }
        },2000);

    }


}
