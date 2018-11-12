package example.homework;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static example.homework.MainActivity.themeId;

public class ContentActivity extends AppCompatActivity {

    private ServiceConnection connection;
    private Intent intentContent;
    private MusicService service;
    private ImageButton play;
    private TextView name;
    private boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        bound = false;

        //Интенты
        intentContent = getIntent();
        Intent intentService = new Intent(this, MusicService.class);

        name = findViewById(R.id.tv_name);
        play = findViewById(R.id.ib_play);

        connection = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder binder) {
                service = ((MusicService.MusicBinder) binder).getService();
                service.releaseMediaPlayer();
                service.setItem(intentContent.getIntExtra("position", 0));
                ContentActivity.this.name.setText(service.getItem().getName());
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };

        //Привязываемся к MusicService
        bindService(intentService, connection, 0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setTheme(themeId);
        recreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!bound) return;
        //Отвязываемся от MusicService
        unbindService(connection);
    }

    public void action(View view) {
        if (!bound) return;
        switch (view.getId()) {
            case R.id.ib_play:
                if (service.play())
                    play.setImageResource(R.drawable.ic_play_green);
                else
                    play.setImageResource(R.drawable.ic_pause_green);
                break;
            case R.id.ib_prev:
                play.setImageResource(R.drawable.ic_play_green);
                name.setText(service.previous());
                break;
            case R.id.ib_after:
                play.setImageResource(R.drawable.ic_play_green);
                name.setText(service.next());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add(0, 1, 0, "settings");
        menuItem.setIntent(new Intent(this, SettingsActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
