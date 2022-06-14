package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.ResultState

interface AuthRepository {

    suspend fun login(baseUser: BaseUser): ResultState<Unit>

    suspend fun register(baseUser: BaseUser)
}