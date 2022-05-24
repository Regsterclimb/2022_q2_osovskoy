package com.example.a2022_q2_osovskoy.data.data_source.settings

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.a2022_q2_osovskoy.data.model.Settings
import javax.inject.Inject

interface SettingsDataSource {

    fun get(): Settings

    fun update(settings: Settings)

    class Base @Inject constructor(private val preferences: SharedPreferences) :
        SettingsDataSource {

        private companion object {
            const val USE_DEV_CONFIG = "use_dev_config"
        }

        override fun get(): Settings =
            Settings(preferences.getBoolean(USE_DEV_CONFIG, false))

        override fun update(settings: Settings) {
            preferences.edit {
                putBoolean(USE_DEV_CONFIG, settings.useLocalConfig)
            }
        }
    }
}