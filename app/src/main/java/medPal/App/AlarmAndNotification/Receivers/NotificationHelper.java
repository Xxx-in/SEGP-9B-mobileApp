package medPal.App.AlarmAndNotification.Receivers;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import medPal.App.R;

public class NotificationHelper {

    public static final int PILL_REMINDER_NOTIFICATION_REQUEST_CODE = 1;
    public static final int APPOINTMENT_NOTIFICATION_REQUEST_CODE = 2;
    public static final int BLOOD_PRESSURE_NOTIFICATION_REQUEST_CODE = 3;
    public static final int BLOOD_SUGAR_NOTIFICATION_REQUEST_CODE = 4;

    public static final int PILL_REMINDER_ONCLICK_REQUEST_CODE = 10001;
    public static final int BLOOD_PRESSURE_ONCLICK_REQUEST_CODE = 10002;
    public static final int BLOOD_SUGAR_ONCLICK_REQUEST_CODE = 10003;

    private final String title = "";
    private final String text = "";
    private final String info = "";

    public static void createNotification(Context context, int id, String channelId, String title, String text, String info) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_baseline_alarm_on_24)
                .setWhen(System.currentTimeMillis())
                .setTicker("Hearty365")
                .setSound(Settings.System.DEFAULT_ALARM_ALERT_URI)
                .setVibrate(new long[] {1000, 1000, 1000, 1000, 1000})
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(title)
                .setContentText(text)
                .setContentInfo(info);

        notificationManager.notify(id, notificationBuilder.build());
    }

    public static void createNotification(Context context, int id, String channelId, String title, String text, String info, PendingIntent contentIntent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_baseline_alarm_on_24)
                .setWhen(System.currentTimeMillis())
                .setTicker("Hearty365")
                .setSound(Settings.System.DEFAULT_ALARM_ALERT_URI)
                .setVibrate(new long[] {1000, 1000, 1000, 1000, 1000})
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(title)
                .setContentText(text)
                .setContentInfo(info)
                .setContentIntent(contentIntent);

        notificationManager.notify(id, notificationBuilder.build());
    }

}
