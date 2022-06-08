package com.example.a2022_q2_osovskoy.data.datasourse.local.appconfig

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.a2022_q2_osovskoy.di.annotations.BasicSharedPrefs
import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue
import javax.inject.Inject

class AppConfigDataSourceImpl @Inject constructor(
    @BasicSharedPrefs private val sharedPreferences: SharedPreferences,
) : AppConfigDataSource {

    companion object {
        const val APP_CONFIG = "app_config_key"
        const val APP_CONFIG_DEFAULT = false
    }

    override suspend fun get(): AppConfigValue = AppConfigValue(
        isLogged = sharedPreferences.getBoolean(APP_CONFIG, APP_CONFIG_DEFAULT)
    )

    override suspend fun update(appConfigValue: AppConfigValue) {
        sharedPreferences.edit {
            putBoolean(APP_CONFIG, appConfigValue.isLogged)
        }
    }
}