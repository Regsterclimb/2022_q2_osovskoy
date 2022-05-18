package com.example.a2022_q2_osovskoy.data.di

import com.example.a2022_q2_osovskoy.data.datasource.StringLocalDataSource
import com.example.a2022_q2_osovskoy.data.datasource.StringLocalLocalDataSourceImpl
import com.example.a2022_q2_osovskoy.data.datasource.StringRemoteDataSource
import com.example.a2022_q2_osovskoy.data.datasource.StringRemoteDataSourceImpl
import com.example.a2022_q2_osovskoy.di.MainActivityComponent
import dagger.Binds
import dagger.Module

@Module(subcomponents = [MainActivityComponent::class])
interface DataBindsModule {

    @Binds
    fun bindStringLocalDataSource(implLocal: StringLocalLocalDataSourceImpl): StringLocalDataSource

    @Binds
    fun bindStringRemoteDataSource(impl: StringRemoteDataSourceImpl): StringRemoteDataSource
}