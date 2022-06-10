package com.example.a2022_q2_osovskoy.data.datasourse.remote

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import okhttp3.ResponseBody

interface AuthDataSource {

    suspend fun login(baseUser: BaseUser) : ResponseBody

    suspend fun register(baseUser: BaseUser)
}