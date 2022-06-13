package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.AppConfigDataSource
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
import javax.inject.Inject

class AppConfigRepositoryImpl @Inject constructor(private val appConfigDataSource: AppConfigDataSource) :
    AppConfigRepository {

    override fun get(): AppConfig = appConfigDataSource.get()

    override fun update(appConfig: AppConfig) {
        appConfigDataSource.update(appConfig)
    }
}