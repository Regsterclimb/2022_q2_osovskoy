package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue

interface AppConfigRepository {

    fun get(): AppConfigValue

    fun update(appConfigValue: AppConfigValue)
}