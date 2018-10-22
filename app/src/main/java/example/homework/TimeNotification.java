package example.homework;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class TimeNotification {

    public void notify(Context context, Intent intent) {
        String cnlID, cnlName, cnlDesc;
        Intent resultIntent;
        Notification notification;
        NotificationChannel channel;
        NotificationCompat.Builder builder;
        NotificationManager notificationManager;

        cnlID = "first_alarm_channel";
        cnlName = "Alarm Channel";
        cnlDesc = "This channel is used by alarm application";

        channel = null;
        resultIntent = new Intent(context, NotifyActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(cnlID, cnlName, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(cnlDesc);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLACK);
        }

        builder = new NotificationCompat.Builder(context, cnlID)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("Alarm")
                .setContentText("Wake up!")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .setOngoing(true);

        notification = builder.build();
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(1, notification);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && channel != null)
                notificationManager.createNotificationChannel(channel);
        }
    }
}
