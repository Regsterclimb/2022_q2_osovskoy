package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [PresentationModule::class])
    fun provideMainActivity(): MainActivity
}