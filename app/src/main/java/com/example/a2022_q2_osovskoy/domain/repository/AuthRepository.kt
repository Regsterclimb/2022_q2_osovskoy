package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.ResultCallBack

interface AuthRepository {

    suspend fun login(baseUser: BaseUser): ResultCallBack

    suspend fun register(baseUser: BaseUser): ResultCallBack
}