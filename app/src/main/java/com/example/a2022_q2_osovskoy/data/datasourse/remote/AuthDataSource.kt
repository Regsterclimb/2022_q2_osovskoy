package com.example.a2022_q2_osovskoy.data.datasourse.remote

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser

interface AuthDataSource {

    suspend fun login(baseUser: BaseUser) : String

    suspend fun register(baseUser: BaseUser)
}