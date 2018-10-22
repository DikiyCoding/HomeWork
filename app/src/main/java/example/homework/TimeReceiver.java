package example.homework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TimeNotification notification = new TimeNotification();
        notification.notify(context, intent);
    }
}
