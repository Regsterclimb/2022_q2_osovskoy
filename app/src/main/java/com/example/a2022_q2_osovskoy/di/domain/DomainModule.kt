package com.example.a2022_q2_osovskoy.di.domain

import com.example.a2022_q2_osovskoy.data.repository.AppConfigRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.AuthRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.BaseRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.LoansRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import com.example.a2022_q2_osovskoy.domain.repository.BaseRepository
import com.example.a2022_q2_osovskoy.domain.repository.LoansRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindLoginRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindAppConfigRepository(impl: AppConfigRepositoryImpl): AppConfigRepository

    @Binds
    fun bindLoansRepository(impl: LoansRepositoryImpl): LoansRepository

    @Binds
    fun bindBaseRepository(impl: BaseRepositoryImpl): BaseRepository
}