package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.di.annotations.ShiftLabBaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module(includes = [RetrofitModule::class])
class NetworkModule {

    companion object {
        const val BASE_URL = "https://shiftlab.cft.ru:7777"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 15L
        const val READ_TIMEOUT = 30L
    }

    @Provides
    fun provideOkHttpClientWithProgress(authInterceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @ShiftLabBaseUrl
    fun provideConventApiBaseUrl(): String = BASE_URL

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}


