package example.homework

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import java.util.*

class MusicService : Service(), MediaPlayer.OnPreparedListener {

    private var mediaPlayer: MediaPlayer? = null
    private var binder: MusicBinder? = null
    private var items: ArrayList<Item>? = null
    var item: Item? = null
        private set
    private var position: Int = 0

    override fun onCreate() {
        super.onCreate()
        binder = MusicBinder()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        items = intent.getParcelableArrayListExtra("list")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    /**
     * Play/Pause
     */
    fun play(): Boolean {
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer!!.pause()
            return true
        } else {
            mediaPlayer!!.start()
            return false
        }
    }

    /**
     * To Previous Soundtrack
     */
    fun previous(): String? {
        if (mediaPlayer == null) return "Error"
        if (position == 0)
            position = items!!.size - 1
        else
            position--
        releaseMediaPlayer()
        setItem(position)
        return item!!.name
    }

    /**
     * To Next Soundtrack
     */
    operator fun next(): String? {
        if (mediaPlayer == null) return "Error"
        if (position == items!!.size - 1)
            position = 0
        else
            position++
        releaseMediaPlayer()
        setItem(position)
        return item!!.name
    }

    /**
     * Binder
     */
    internal inner class MusicBinder : Binder() {
        val service: MusicService
            get() = this@MusicService
    }

    /**
     * Getters/Setters
     */
    fun setItem(position: Int) {
        item = items!![position]
        mediaPlayer = MediaPlayer.create(this, item!!.id)
    }

    /**
     * Сleaning Up Resources
     */
    fun releaseMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer!!.release()
                mediaPlayer = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
