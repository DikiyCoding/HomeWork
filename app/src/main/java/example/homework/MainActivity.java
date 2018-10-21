package example.homework;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private TimePicker timePicker;
    private int currentMinutes, currentHours;
    public static final String cnlID = "first_alarm_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        pendingIntent = PendingIntent.getBroadcast(this, 0,
                new Intent(this, Receiver.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        createChannel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentTime();
        refreshTimePicker();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void handle(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                long difference;
                int alarmHours, alarmMinutes;

                setCurrentTime();
                alarmHours = timePicker.getHour();
                alarmMinutes = timePicker.getMinute();
                if (alarmHours >= currentHours)
                    difference = alarmHours - currentHours;
                else
                    difference = alarmHours + 24 - currentHours;
                difference = (difference * 60 + Math.abs(alarmMinutes - currentMinutes)) * 60 * 1000;
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + difference, pendingIntent);
                break;
            case R.id.btnStop:
                alarmManager.cancel(pendingIntent);
        }
    }

    public void createChannel() {
        String cnlName = "Alarm Channel";
        String cnlDesc = "This channel is used by alarm application";
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(cnlID, cnlName, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(cnlDesc);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLACK);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public void setCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        currentHours = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinutes = calendar.get(Calendar.MINUTE);
    }

    public void refreshTimePicker() {
        timePicker.setCurrentHour(currentHours);
        timePicker.setCurrentMinute(currentMinutes);
    }
}
