package com.example.a2022_q2_osovskoy.data.datasourse.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.a2022_q2_osovskoy.di.annotations.BasicSharedPrefs
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import javax.inject.Inject

class AppConfigDataSourceImpl @Inject constructor(
    @BasicSharedPrefs private val sharedPreferences: SharedPreferences,
) : AppConfigDataSource {

    companion object {
        const val APP_CONFIG = "app_config_key"
        const val APP_CONFIG_DEFAULT = 0
        const val APP_CONFIG_UNINSTRUCTED = 1
    }

    override fun get(): AppConfig =
        when (sharedPreferences.getInt(APP_CONFIG, APP_CONFIG_DEFAULT)) {
            APP_CONFIG_DEFAULT -> AppConfig.UNAUTHORIZED
            APP_CONFIG_UNINSTRUCTED -> AppConfig.UNINSTRUCTED
            else -> AppConfig.BASE
        }

    override fun update(appConfigValue: AppConfig) {
        sharedPreferences.edit {
            putInt(APP_CONFIG, appConfigValue.ordinal)
        }
    }
}