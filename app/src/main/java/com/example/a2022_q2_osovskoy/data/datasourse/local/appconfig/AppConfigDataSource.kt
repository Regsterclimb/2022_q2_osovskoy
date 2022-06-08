package com.example.a2022_q2_osovskoy.data.datasourse.local.appconfig

import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue
//todo rename
interface AppConfigDataSource {

    suspend fun get(): AppConfigValue

    suspend fun update(appConfigValue: AppConfigValue)
}