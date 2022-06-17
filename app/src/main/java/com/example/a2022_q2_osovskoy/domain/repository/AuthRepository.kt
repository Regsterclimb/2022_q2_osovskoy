package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser

interface AuthRepository {

    suspend fun login(baseUser: BaseUser)

    suspend fun register(baseUser: BaseUser)
}