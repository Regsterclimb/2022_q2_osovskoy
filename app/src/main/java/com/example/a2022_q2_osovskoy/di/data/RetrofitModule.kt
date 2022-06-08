package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.datasourse.network.AuthApi
import com.example.a2022_q2_osovskoy.data.datasourse.network.LoansApi
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

    @Provides
    @AppScope //todo()
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @AppScope //todo()
    fun provideLoansApi(retrofit: Retrofit): LoansApi = retrofit.create(LoansApi::class.java)
}