package example.homework

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.main.*
import java.lang.IllegalArgumentException
import java.util.*

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val items: ArrayList<Item> = ArrayList()
    private var intentContent: Intent? = null
    private var intentService: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getCurrentTheme()
        setTheme(themeId)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        intentContent = Intent(this, ContentActivity::class.java)
        intentService = Intent(this, MusicService::class.java)

        setItems()

        val adapter = object : Adapter(this, items!!) {
            override fun onItemClicked(position: Int) {
                intentContent!!.putExtra("position", position)
                startActivity(intentContent)
            }
        }
        list.adapter = adapter

        intentService?.putExtra("list", items)
        startService(intentService)
    }

    override fun onRestart() {
        super.onRestart()
        setTheme(themeId)
        recreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(intentService)
    }

    private fun setItems() {
        items.add(Item("Europe – It's a Final Countdown", R.raw.europe_its_a_final_countdown))
        items.add(Item("Haddaway – What is Love", R.raw.haddaway_what_is_love))
        items.add(Item("RickRoll'D – Never Gonna Give You Up", R.raw.rickrolld_never_gonna_give_you_up))
        items.add(Item("Roxette – Listen to Your Heart", R.raw.roxette_listen_to_your_heart))
        items.add(Item("Savage – Only You", R.raw.savage_only_you))
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        setThemeId(sharedPreferences.getString(key, "blue")!!)
    }

    private fun getCurrentTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        setThemeId(sharedPreferences.getString("current_theme", "blue")!!)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.add(0, 1, 0, "settings")
        menuItem.intent = Intent(this, SettingsActivity::class.java)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {

        var themeId: Int = 0

        fun setThemeId(value: String) {
            when (value) {
                "red" -> themeId = R.style.AppThemeRed
                "yellow" -> themeId = R.style.AppThemeYellow
                "green" -> themeId = R.style.AppThemeGreen
                "blue" -> themeId = R.style.AppThemeBlue
                else -> IllegalArgumentException("Invalid theme id value")
            }
        }
    }
}
