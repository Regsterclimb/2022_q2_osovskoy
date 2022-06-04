package com.example.a2022_q2_osovskoy.di.domain

import com.example.a2022_q2_osovskoy.data.repository.TimerRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.repository.TimerRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindMainRepository(impl: TimerRepositoryImpl): TimerRepository
}