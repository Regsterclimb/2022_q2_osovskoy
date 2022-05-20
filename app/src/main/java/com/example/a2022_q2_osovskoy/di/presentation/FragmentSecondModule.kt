package com.example.a2022_q2_osovskoy.di.presentation

import com.example.a2022_q2_osovskoy.di.annotations.FragmentSecondScope
import com.example.a2022_q2_osovskoy.ui.second_screen.FragmentSecond
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentSecondModule {

    @ContributesAndroidInjector
    @FragmentSecondScope
    fun injectShiftFragment(): FragmentSecond
}