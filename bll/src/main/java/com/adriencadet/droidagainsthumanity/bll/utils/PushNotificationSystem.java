package com.adriencadet.droidagainsthumanity.bll.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * PushNotificationSystem
 * <p>
 */
public class PushNotificationSystem {
    private int     counter;
    private Context context;
    private int     smallLogoResourceId;
    private int     largeLogoResourceId;
    private String  groupKey;

    public PushNotificationSystem(Context context, int smallLogoResourceId, int largeLogoResourceId, String groupKey) {
        this.counter = 0;
        this.context = context;
        this.smallLogoResourceId = smallLogoResourceId;
        this.largeLogoResourceId = largeLogoResourceId;
        this.groupKey = groupKey;
    }

    public void notify(String title, String message) {
        notify(title, message, null);
    }

    public void notify(String title, String message, Intent extraIntent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        builder
            .setSmallIcon(smallLogoResourceId)
            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeLogoResourceId))
            .setDefaults(
                Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND
                | Notification.VISIBILITY_PUBLIC | Notification.FLAG_AUTO_CANCEL
            )
            .setCategory(Notification.CATEGORY_MESSAGE)
            .setContentTitle(title)
            .setContentText(message)
            .setOnlyAlertOnce(true)
            .setGroup(groupKey)
            .setAutoCancel(true);

        if (extraIntent != null) {
            builder.setContentIntent(
                PendingIntent.getActivity(context, 0, extraIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            );
        }

        manager.notify(counter++, builder.build());
    }
}
