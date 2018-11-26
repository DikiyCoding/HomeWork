package example.homework

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import example.homework.MainActivity.Companion.themeId

class ContentActivity : AppCompatActivity() {

    private var connection: ServiceConnection? = null
    private var intentContent: Intent? = null
    private var service: MusicService? = null
    private var play: ImageButton? = null
    private var name: TextView? = null
    private var bound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(themeId)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)

        bound = false

        //Интенты
        intentContent = intent
        val intentService = Intent(this, MusicService::class.java)

        name = findViewById(R.id.tv_name)
        play = findViewById(R.id.ib_play)

        connection = object : ServiceConnection {

            override fun onServiceConnected(name: ComponentName, binder: IBinder) {
                service = (binder as MusicService.MusicBinder).service
                service!!.releaseMediaPlayer()
                service!!.setItem(intentContent!!.getIntExtra("position", 0))
                this@ContentActivity.name!!.text = service!!.item!!.name
                bound = true
            }

            override fun onServiceDisconnected(name: ComponentName) {
                bound = false
            }
        }

        //Привязываемся к MusicService
        bindService(intentService, connection, 0)
    }

    override fun onRestart() {
        super.onRestart()
        setTheme(themeId)
        recreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!bound) return
        //Отвязываемся от MusicService
        unbindService(connection)
    }

    fun action(view: View) {
        if (!bound) return
        when (view.id) {
            R.id.ib_play -> if (service!!.play())
                play!!.setImageResource(R.drawable.ic_play_green)
            else
                play!!.setImageResource(R.drawable.ic_pause_green)
            R.id.ib_prev -> {
                play!!.setImageResource(R.drawable.ic_play_green)
                name!!.text = service!!.previous()
            }
            R.id.ib_after -> {
                play!!.setImageResource(R.drawable.ic_play_green)
                name!!.text = service!!.next()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.add(0, 1, 0, "settings")
        menuItem.intent = Intent(this, SettingsActivity::class.java)
        return super.onCreateOptionsMenu(menu)
    }
}
