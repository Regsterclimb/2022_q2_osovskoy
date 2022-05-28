package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.data_source.remote.support.ProgressInterceptor
import com.example.a2022_q2_osovskoy.di.annotations.ConventApiBaseUrl
import com.example.a2022_q2_osovskoy.di.annotations.OkHttpClientWithProgress
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [RetrofitModule::class])
class NetworkModule {

    companion object {
        const val BASE_URL = "https://v2.convertapi.com"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 15L
        const val READ_TIMEOUT = 30L
    }

    @Provides
    @OkHttpClientWithProgress
    fun provideOkHttpClientWithProgress(progressInterceptor: ProgressInterceptor) =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(progressInterceptor)
            .build()

    @Provides
    @ConventApiBaseUrl
    fun provideConventApiBaseUrl(): String = BASE_URL

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}


