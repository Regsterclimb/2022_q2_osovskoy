package com.example.a2022_q2_osovskoy.data.data_source.network

import com.example.a2022_q2_osovskoy.data.model.FilePostResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ConventApi {
    @Multipart
    @POST("/upload")
    suspend fun sendFile(@Part file: MultipartBody.Part): FilePostResponse
}