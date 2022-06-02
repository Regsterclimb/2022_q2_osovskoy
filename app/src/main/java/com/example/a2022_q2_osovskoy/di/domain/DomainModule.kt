package com.example.a2022_q2_osovskoy.di.domain

import com.example.a2022_q2_osovskoy.data.repository.MainRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.repository.MainRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository
}