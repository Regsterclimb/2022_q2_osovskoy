package com.example.a2022_q2_osovskoy.di.data

import android.content.Context
import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.data.datasourse.local.*
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthRemoteDataSourceImpl
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansRemoteDataSourceImpl
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module(includes = [
    NetworkModule::class,
    RoomDataBaseModule::class,
    SharedPrefModule::class]
)
interface DataModule {

    companion object {

        @Provides
        fun provideDispatcherIo(): CoroutineDispatcher = Dispatchers.IO
    }

    @Binds
    fun provideContext(app:App) : Context

    @Binds
    @AppScope
    fun bindAppConfigDataSource(impl: AppConfigDataSourceImpl): AppConfigDataSource

    @Binds
    fun bindLoansLocalDataSource(impl: LoansLocalDataSourceImpl): LoansLocalDataSource

    @Binds
    fun bindLoginDataSource(implRemote: AuthRemoteDataSourceImpl): AuthDataSource

    @Binds
    fun bindLoansDataSource(implRemote: LoansRemoteDataSourceImpl): LoansDataSource

    @Binds
    fun bindTokenDataSource(implLocal: TokenDataSourceImpl): TokenDataSource

}