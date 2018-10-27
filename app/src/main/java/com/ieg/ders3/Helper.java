package com.ieg.ders3;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class Helper {
    public static String SMS_INTENT = "my_sms";
    public static String SMS_VALIDATE = "sms_validate";

    public static void ShowNoti(Context context, String title, String body) {
        Notification noti = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, noti);
    }
}
