package com.example.a2022_q2_osovskoy.data.datasourse.local.appconfig

import com.example.a2022_q2_osovskoy.domain.entity.AppConfig

interface AppConfigDataSource {

    fun get(): AppConfig

    fun update(appConfigValue: AppConfig)
}