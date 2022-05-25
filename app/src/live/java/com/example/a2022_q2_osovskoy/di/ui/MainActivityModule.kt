package com.example.a2022_q2_osovskoy.di.ui

import com.example.a2022_q2_osovskoy.di.annotations.MainActivityScope
import com.example.a2022_q2_osovskoy.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [ShiftFragmentModule::class])
    @MainActivityScope
    fun injectMainActivity(): MainActivity
}