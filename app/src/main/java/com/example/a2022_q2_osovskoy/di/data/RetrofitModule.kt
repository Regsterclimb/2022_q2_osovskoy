package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.data_source.network.ConventApi
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.di.annotations.ConventApiBaseUrl
import com.example.a2022_q2_osovskoy.di.annotations.OkHttpClientWithProgress
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
        @ConventApiBaseUrl baseUrl: String,
        @OkHttpClientWithProgress httpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @AppScope
    fun provideConventApi(retrofit: Retrofit): ConventApi = retrofit.create(ConventApi::class.java)
}