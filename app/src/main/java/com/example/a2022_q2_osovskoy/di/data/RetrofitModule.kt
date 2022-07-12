package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.di.annotations.ShiftLabBaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    @AppScope
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @ShiftLabBaseUrl baseUrl: String,
        httpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .build()
}