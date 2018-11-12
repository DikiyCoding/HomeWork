package example.homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Intent intentContent, intentService;
    private ArrayList<Item> items;

    public static int themeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getCurrentTheme();
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        intentContent = new Intent(this, ContentActivity.class);
        intentService = new Intent(this, MusicService.class);

        final RecyclerView list = findViewById(R.id.list);
        setItems();

        Adapter adapter = new Adapter(this, items) {
            @Override
            public void onItemClicked(int position) {
                intentContent.putExtra("position", position);
                startActivity(intentContent);
            }
        };
        list.setAdapter(adapter);

        intentService.putExtra("list", items);
        startService(intentService);
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
        stopService(intentService);
    }

    private void setItems() {
        items = new ArrayList<>();
        items.add(new Item("Europe – It's a Final Countdown", R.raw.europe_its_a_final_countdown));
        items.add(new Item("Haddaway – What is Love", R.raw.haddaway_what_is_love));
        items.add(new Item("RickRoll'D – Never Gonna Give You Up", R.raw.rickrolld_never_gonna_give_you_up));
        items.add(new Item("Roxette – Listen to Your Heart", R.raw.roxette_listen_to_your_heart));
        items.add(new Item("Savage – Only You", R.raw.savage_only_you));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        setThemeId(sharedPreferences.getString(key, "blue"));
    }

    private void getCurrentTheme() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        setThemeId(sharedPreferences.getString("current_theme", "blue"));
    }

    public static void setThemeId(String value) {
        switch (value) {
            case "red":
                themeId = R.style.AppThemeRed;
                break;
            case "yellow":
                themeId = R.style.AppThemeYellow;
                break;
            case "green":
                themeId = R.style.AppThemeGreen;
                break;
            case "blue":
                themeId = R.style.AppThemeBlue;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add(0, 1, 0, "settings");
        menuItem.setIntent(new Intent(this, SettingsActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
