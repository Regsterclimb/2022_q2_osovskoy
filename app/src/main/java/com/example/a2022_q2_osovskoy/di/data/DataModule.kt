package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.data_source.ItemsDataSource
import com.example.a2022_q2_osovskoy.data.data_source.ItemsDataSourceImpl
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @AppScope
    fun bindItemsDataSource(impl: ItemsDataSourceImpl): ItemsDataSource

}