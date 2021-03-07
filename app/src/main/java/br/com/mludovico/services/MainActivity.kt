package br.com.mludovico.services

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import br.com.mludovico.services.sync.DrinkWaterRemindIntentService
import br.com.mludovico.services.sync.DrinkWaterReminderTask
import br.com.mludovico.services.utils.PreferencesUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateWaterCount()

        image.setOnClickListener {
            incrementWaterHandler()
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    private fun updateWaterCount() {
        val count = PreferencesUtils.getWaterCount(this)
        text_view.text = "$count"
    }

    private fun incrementWaterHandler() {
        val intent = Intent(this, DrinkWaterRemindIntentService::class.java)
        intent.action = DrinkWaterReminderTask.ACTION_INCREMENT_WATER_COUNT
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (PreferencesUtils.KEY_WATER_COUNT == key) {
            updateWaterCount()
        }
    }
}