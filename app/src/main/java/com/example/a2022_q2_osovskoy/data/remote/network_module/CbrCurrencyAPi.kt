package com.example.a2022_q2_osovskoy.data.remote.network_module

import com.example.a2022_q2_osovskoy.data.remote.responses.ApiData
import retrofit2.http.GET

interface CbrCurrencyApi {
    @GET("/daily_json.js")
    suspend fun loadDataFromApi(): ApiData
}