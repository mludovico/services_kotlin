package br.com.mludovico.services.sync

import android.content.Context
import br.com.mludovico.services.utils.PreferencesUtils

class DrinkWaterReminderTask {

    companion object {
        const val ACTION_INCREMENT_WATER_COUNT = "action-increment-water-count"

        private fun incrementWaterCount(context: Context) {
            PreferencesUtils.incrementWaterCount(context)
        }

        fun executeTask(context: Context, action: String?) {
            if (ACTION_INCREMENT_WATER_COUNT == action) {
                incrementWaterCount(context)
            }
        }
    }
}