package com.example.a2022_q2_osovskoy.di.domain

import com.example.a2022_q2_osovskoy.data.repository.FileRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.repository.FileRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindFileRepository(impl:FileRepositoryImpl):FileRepository
}