package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.datasourse.local.TokenDataSource
import com.example.a2022_q2_osovskoy.di.annotations.ShiftLabBaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module(includes = [RetrofitModule::class,ApiModule::class])
class NetworkModule {

    companion object {
        const val BASE_URL = "https://shiftlab.cft.ru:7777"
        const val CONNECT_TIMEOUT = 5L
        const val WRITE_TIMEOUT = 5L
        const val READ_TIMEOUT = 5L
        const val NO_AUTH_HEADER = "No-Authentication"
        const val AUTHORIZATION_HEADER = "Authorization"
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
    fun provideAuthInterceptor(tokenDataSource: TokenDataSource): Interceptor =
        Interceptor { chain ->
            var request = chain.request()
            if (request.header(NO_AUTH_HEADER) == null) {
                request = request.newBuilder()
                    .addHeader(AUTHORIZATION_HEADER, tokenDataSource.get())
                    .build()
            }
            chain.proceed(request)
        }

    @Provides
    @ShiftLabBaseUrl
    fun provideConventApiBaseUrl(): String = BASE_URL

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}


