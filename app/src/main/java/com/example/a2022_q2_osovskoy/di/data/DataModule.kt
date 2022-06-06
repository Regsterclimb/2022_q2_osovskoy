package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoginDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [NetworkModule::class])
interface DataModule {

    companion object {
        @Provides
        fun provideDispatcherIo(): CoroutineDispatcher = Dispatchers.IO
    }

    @Binds
    fun bindLoginDataSource(impl: LoginDataSourceImpl): LoginDataSource
}