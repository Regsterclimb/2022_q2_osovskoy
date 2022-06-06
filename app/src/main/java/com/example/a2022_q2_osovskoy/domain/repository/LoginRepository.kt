package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.LoginState

interface LoginRepository {

    suspend fun logIn(baseUser: BaseUser) : LoginState

    suspend fun register(baseUser: BaseUser) : LoginState
}