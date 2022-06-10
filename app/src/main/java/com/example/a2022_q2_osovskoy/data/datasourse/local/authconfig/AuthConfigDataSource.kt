package com.example.a2022_q2_osovskoy.data.datasourse.local.authconfig

import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState

interface AuthConfigDataSource {

    fun get(): LoginConfigState

    fun update(loginConfigState: LoginConfigState)
}