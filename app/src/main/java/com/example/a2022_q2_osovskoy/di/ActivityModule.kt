package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.MainActivity
import com.example.a2022_q2_osovskoy.di.presentation.PresentationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class,PresentationModule::class])
    fun provideMainActivity(): MainActivity
}