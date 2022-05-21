package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.datasource.StringDataSource
import com.example.a2022_q2_osovskoy.data.datasource.StringLocalDataSourceImpl
import com.example.a2022_q2_osovskoy.data.datasource.StringRemoteDataSourceImpl
import com.example.a2022_q2_osovskoy.di.annotation.Local
import com.example.a2022_q2_osovskoy.di.annotation.Remote
import dagger.Binds
import dagger.Module

@Module
interface DataBindsModule {

    @Binds
    @Local
    fun bindStringLocalDataSource(implLocal: StringLocalDataSourceImpl): StringDataSource

    @Binds
    @Remote
    fun bindStringRemoteDataSource(implRemote: StringRemoteDataSourceImpl): StringDataSource
}