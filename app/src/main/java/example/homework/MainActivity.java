package example.homework;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        pendingIntent = PendingIntent.getBroadcast(this, 0,
                new Intent(this, TimeReceiver.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
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
