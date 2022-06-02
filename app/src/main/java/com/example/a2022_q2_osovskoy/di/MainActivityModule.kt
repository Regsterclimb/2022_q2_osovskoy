package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.di.annotations.MainActivityScope
import com.example.a2022_q2_osovskoy.di.presentation.PresentationModule
import com.example.a2022_q2_osovskoy.di.ui.UiModule
import com.example.a2022_q2_osovskoy.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PresentationModule::class, UiModule::class])
interface MainActivityModule {

    @ContributesAndroidInjector
    @MainActivityScope
    fun bindMainActivity(): MainActivity

}