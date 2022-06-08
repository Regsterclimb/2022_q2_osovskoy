package com.example.a2022_q2_osovskoy.data.datasourse.local.authconfig

import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState

interface AuthConfigDataSource {

    suspend fun get(): LoginConfigState

    suspend fun update(loginConfigState: LoginConfigState)
}