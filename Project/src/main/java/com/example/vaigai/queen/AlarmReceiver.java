package com.example.vaigai.queen;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 0;





    public AlarmReceiver() {

    }



    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        Intent contentIntent = new Intent(context, settings.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.preg11)
                .setContentTitle("Remainder")
                .setContentText("food")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
        Toast.makeText(context, "alarm", Toast.LENGTH_LONG).show();
        //throw new UnsupportedOperationException("Not yet implemented");

    }

}
