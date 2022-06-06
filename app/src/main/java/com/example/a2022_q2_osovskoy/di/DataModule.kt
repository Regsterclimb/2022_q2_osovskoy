package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.data.datasourse.*
import com.maxsch.rxjavalecture.di.annotations.CatsDataSource
import com.maxsch.rxjavalecture.di.annotations.DogsDataSource
import com.maxsch.rxjavalecture.di.annotations.RatsDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
interface DataModule {

    companion object{
        @Provides
        fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

    @[Binds CatsDataSource]
    fun bindCatsData(impl: CatsDataSourceImpl): AnimalDataSource

    @[Binds DogsDataSource]
    fun bindDogsData(impl: DogsDataSourceImpl): AnimalDataSource

    @[Binds RatsDataSource]
    fun bindRatsData(impl: RatsDataSourceImpl): AnimalDataSource

    @Binds
    fun bindPriceData(impl: PriceDataSourceImpl): PriceDataSource
}