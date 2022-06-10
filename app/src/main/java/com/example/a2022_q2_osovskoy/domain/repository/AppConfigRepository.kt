package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.AppConfig

interface AppConfigRepository {

    fun get(): AppConfig

    fun update(appConfig: AppConfig)
}