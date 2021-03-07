package br.com.mludovico.services.sync

import android.app.IntentService
import android.content.Intent

class DrinkWaterRemindIntentService: IntentService("DrinkWaterRemindIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        val action = intent?.action
        DrinkWaterReminderTask.executeTask(this, action)
    }
}