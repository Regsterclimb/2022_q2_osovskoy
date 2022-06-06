package com.example.a2022_q2_osovskoy.data.datasourse.network

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApi {

    @POST("/login")
    suspend fun login(@Body baseUser: BaseUser)

    @POST("/registration")
    suspend fun register(@Body baseUser: BaseUser)
}