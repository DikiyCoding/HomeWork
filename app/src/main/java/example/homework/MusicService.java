package example.homework;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayer;
    private MusicBinder binder;
    private ArrayList<Item> items;
    private Item item;
    private int position;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MusicBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        items = intent.getParcelableArrayListExtra("list");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    /**
     * Play/Pause
     */
    public boolean play() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            return true;
        } else {
            mediaPlayer.start();
            return false;
        }
    }

    /**
     * To Previous Soundtrack
     */
    public String previous() {
        if (mediaPlayer == null) return "Error";
        if (position == 0)
            position = items.size() - 1;
        else
            position--;
        releaseMediaPlayer();
        setItem(position);
        return item.getName();
    }

    /**
     * To Next Soundtrack
     */
    public String next() {
        if (mediaPlayer == null) return "Error";
        if (position == items.size() - 1)
            position = 0;
        else
            position++;
        releaseMediaPlayer();
        setItem(position);
        return item.getName();
    }

    /**
     * Binder
     */
    class MusicBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    /**
     * Getters/Setters
     */
    public void setItem(int position) {
        item = items.get(position);
        mediaPlayer = MediaPlayer.create(this, item.getId());
    }

    public Item getItem() {
        return item;
    }

    /**
     * Сleaning Up Resources
     */
    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
