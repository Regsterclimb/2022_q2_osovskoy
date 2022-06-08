package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue

interface AppConfigRepository {

    suspend fun get(): AppConfigValue

    suspend fun update(appConfigValue: AppConfigValue)
}