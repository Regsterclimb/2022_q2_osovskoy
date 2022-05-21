package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.di.annotation.MainActivityScope
import com.example.a2022_q2_osovskoy.di.presentation.PresentationBindsModule
import com.example.a2022_q2_osovskoy.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PresentationBindsModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @MainActivityScope
    fun contributeMainActivity(): MainActivity
}
