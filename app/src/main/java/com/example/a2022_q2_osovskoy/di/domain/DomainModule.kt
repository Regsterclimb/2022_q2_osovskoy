package com.example.a2022_q2_osovskoy.di.domain

import com.example.a2022_q2_osovskoy.data.repository.ItemsRepositoryImpl
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.domain.repository.ItemsRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @AppScope
    fun bindItemsRepository(impl : ItemsRepositoryImpl) : ItemsRepository
}