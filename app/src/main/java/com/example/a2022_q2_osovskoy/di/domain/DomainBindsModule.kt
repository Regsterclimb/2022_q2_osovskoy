package com.example.a2022_q2_osovskoy.di.domain

import com.example.a2022_q2_osovskoy.data.repository.StringRepositoryImpl
import com.example.a2022_q2_osovskoy.di.annotation.AppScope
import com.example.a2022_q2_osovskoy.domain.repository.StringRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainBindsModule {
    @Binds
    @AppScope
    fun bindStringRepository(impl: StringRepositoryImpl): StringRepository
}