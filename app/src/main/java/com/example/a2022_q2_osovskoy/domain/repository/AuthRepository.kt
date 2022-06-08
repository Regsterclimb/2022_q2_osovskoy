package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.AuthResult
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState

interface AuthRepository {

    suspend fun login(baseUser: BaseUser): AuthResult

    suspend fun register(baseUser: BaseUser): AuthResult

    suspend fun getAuthConfig(): LoginConfigState

    suspend fun updateAuthConfig(loginConfigState: LoginConfigState)
}