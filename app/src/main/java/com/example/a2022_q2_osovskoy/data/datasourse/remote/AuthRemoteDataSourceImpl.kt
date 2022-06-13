package com.example.a2022_q2_osovskoy.data.datasourse.remote

import com.example.a2022_q2_osovskoy.data.datasourse.network.AuthApi
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import okhttp3.ResponseBody
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(private val authApi: AuthApi) : AuthDataSource {

    override suspend fun login(baseUser: BaseUser): ResponseBody = authApi.login(baseUser)

    override suspend fun register(baseUser: BaseUser) {
        authApi.register(baseUser)
    }
}