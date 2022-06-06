package com.example.a2022_q2_osovskoy.di.domain

import com.example.a2022_q2_osovskoy.data.repository.LoginRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}