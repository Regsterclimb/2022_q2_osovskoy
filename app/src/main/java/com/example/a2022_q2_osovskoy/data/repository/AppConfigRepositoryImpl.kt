package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.appconfig.AppConfigDataSource
import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
import javax.inject.Inject

class AppConfigRepositoryImpl @Inject constructor(private val appConfigDataSource: AppConfigDataSource) :
    AppConfigRepository {

    override fun get(): AppConfigValue = appConfigDataSource.get()

    override fun update(appConfigValue: AppConfigValue) {
        appConfigDataSource.update(appConfigValue)
    }
}