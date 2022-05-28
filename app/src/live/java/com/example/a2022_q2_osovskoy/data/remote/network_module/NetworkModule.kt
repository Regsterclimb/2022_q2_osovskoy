package com.example.a2022_q2_osovskoy.data.remote.network_module

import com.example.a2022_q2_osovskoy.data.remote.responses.ApiData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

interface NetworkModule {

    suspend fun loadCurrenciesData(): ApiData

    class RetrofitModule : CbrCurrencyApi, NetworkModule {
        companion object {
            private const val LOCAL_HOST_URL = "https://www.cbr-xml-daily.ru"
        }

        private val json = Json {
            ignoreUnknownKeys = true
        }

        private val httpClient = OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(LOCAL_HOST_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        private fun currencyDataResponse(): CbrCurrencyApi = retrofit.create()

        override suspend fun loadDataFromApi(): ApiData =
            currencyDataResponse().loadDataFromApi()

        override suspend fun loadCurrenciesData(): ApiData =
            loadDataFromApi()
    }
}

