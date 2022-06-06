package com.example.a2022_q2_osovskoy.data.datasourse.remote

import com.example.a2022_q2_osovskoy.data.datasourse.network.LoginApi
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(private val loginApi: LoginApi) : LoginDataSource {

    override suspend fun login(baseUser: BaseUser) {
        loginApi.login(baseUser)
    }

    override suspend fun register(baseUser: BaseUser) {
        loginApi.register(baseUser)
    }
}