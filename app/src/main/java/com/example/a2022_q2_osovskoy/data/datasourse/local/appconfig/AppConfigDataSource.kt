package com.example.a2022_q2_osovskoy.data.datasourse.local.appconfig

import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue
//todo rename
interface AppConfigDataSource {

    fun get(): AppConfigValue

    fun update(appConfigValue: AppConfigValue)
}