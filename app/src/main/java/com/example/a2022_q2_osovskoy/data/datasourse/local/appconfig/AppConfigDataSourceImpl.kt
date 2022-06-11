package com.example.a2022_q2_osovskoy.data.datasourse.local.appconfig

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
    }

    override fun get(): AppConfig =
        when (sharedPreferences.getInt(APP_CONFIG, APP_CONFIG_DEFAULT)) {
            0 -> AppConfig.UNAUTHORIZED
            1 -> AppConfig.AUTHORIZED
            2 -> AppConfig.UNINSTRUCTED
            else -> AppConfig.BASE
        }

    override fun update(appConfigValue: AppConfig) {
        sharedPreferences.edit {
            putInt(APP_CONFIG, appConfigValue.ordinal)
        }
    }
}