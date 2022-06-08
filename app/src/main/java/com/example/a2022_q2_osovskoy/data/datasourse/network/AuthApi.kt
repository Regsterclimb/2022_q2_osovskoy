package com.example.a2022_q2_osovskoy.data.datasourse.network

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface AuthApi {

    @POST("/login")
    @Headers("No-Authentication:true")
    suspend fun login(@Body baseUser: BaseUser): ResponseBody

    @POST("/registration")
    @Headers("No-Authentication:true")
    suspend fun register(@Body baseUser: BaseUser)
}