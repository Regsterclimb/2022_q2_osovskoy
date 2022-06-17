package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.datasourse.network.AuthApi
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.mock.BehaviorDelegate

class AuthApiImpl(private val delegate: BehaviorDelegate<AuthApi>) : AuthApi {

    companion object {
        const val BEARER = "bearer 15151515151"
    }

    override suspend fun login(baseUser: BaseUser): ResponseBody {

        val responseBody = BEARER.toResponseBody()

        return delegate.returningResponse(responseBody).login(baseUser)
    }

    override suspend fun register(baseUser: BaseUser) {
        delegate.returningResponse(Unit).register(baseUser)
    }

}