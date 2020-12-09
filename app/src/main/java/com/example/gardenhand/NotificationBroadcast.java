package com.example.gardenhand;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.gardenhand.ui.login.GardenerLogin;

public class NotificationBroadcast extends BroadcastReceiver {
    public static final String ID = "Notification";
    public static final String TITLE = "Reminder";
    public static final String MESSAGE ="Water plant";
    public static final String CHANNEL = "plant";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, GardenerLogin.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, intent.getStringExtra(CHANNEL))
                .setSmallIcon(R.drawable.ic_stat_flower)
                .setContentTitle( intent.getStringExtra(TITLE))
                .setContentText( intent.getStringExtra(MESSAGE))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(intent.getIntExtra(ID, 100), builder.build());
    }
}