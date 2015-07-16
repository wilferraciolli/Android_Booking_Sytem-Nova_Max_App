package com.wiltech.novamaxapp.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.wiltech.novamaxapp.MainActivity;
import com.wiltech.novamaxapp.R;

public class RemindUserService extends Service {

    //declare the TAf filter
    private static final String TAG = "com.wiltech.novamaxapp.services";

    //create an object for notification - Globals
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;


    public RemindUserService() {
    }

    //service stats
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStart called");
        //return super.onStartCommand(intent, flags, startId);

        //build the notification
        notification = new NotificationCompat.Builder(this);
        //clear the notification once clicked
        notification.setAutoCancel(true);

        //create the threat
        Runnable thread = new Runnable() {
            @Override
            public void run() {

                synchronized (this){
                    try {
                        wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //build the notification - icon/text/time
                //icon
                notification.setSmallIcon(R.drawable.ic_launcher);
                //ticker
                notification.setTicker("We have been missing you, have a voucher!");
                //time
                notification.setWhen(System.currentTimeMillis());
                //Content
                notification.setContentTitle("Voucher");
                notification.setContentText("there is a voucher for you based on your interests");

                //manage what to do once clicked
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);

                //Builds notification and issues it
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID, notification.build());

            }
        };


        Thread novaThread = new Thread(thread);
        novaThread.start();
        return Service.START_STICKY;
    }

    //servevice destroyed
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy called");
        //super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
