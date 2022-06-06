package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.data.repository.AnimalRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.repository.AnimalRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindAnimalRepository(impl: AnimalRepositoryImpl): AnimalRepository
}