package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.datasourse.network.AuthApi
import com.example.a2022_q2_osovskoy.data.datasourse.network.LoansApi
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    private fun getMockAuthRetrofit(retrofit: Retrofit): AuthApi {
        val networkBehavior = NetworkBehavior.create()
        networkBehavior.setDelay(200L,TimeUnit.MILLISECONDS)
        networkBehavior.setFailurePercent(0)

        val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build()

        val delegate = mockRetrofit.create(AuthApi::class.java)
        return AuthApiImpl(delegate)
    }

    private fun getMockLoansRetrofit(retrofit: Retrofit): LoansApi {
        val networkBehavior = NetworkBehavior.create()
        networkBehavior.setDelay(200L,TimeUnit.MILLISECONDS)
        networkBehavior.setFailurePercent(0)
        val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build()
        val delegate = mockRetrofit.create(LoansApi::class.java)
        return LoansApiImpl(delegate)
    }

    @Provides
    @AppScope
    fun provideAuthApi(retrofit: Retrofit): AuthApi = getMockAuthRetrofit(retrofit)

    @Provides
    @AppScope
    fun provideLoansApi(retrofit: Retrofit): LoansApi = getMockLoansRetrofit(retrofit)
}