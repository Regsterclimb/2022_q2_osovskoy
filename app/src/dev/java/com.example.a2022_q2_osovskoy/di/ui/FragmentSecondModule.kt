package com.example.a2022_q2_osovskoy.di.ui

import com.example.a2022_q2_osovskoy.di.annotations.FragmentSecondScope
import com.example.a2022_q2_osovskoy.ui.second_screen.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentSecondModule {

    @ContributesAndroidInjector
    @FragmentSecondScope
    fun injectShiftFragment(): SecondFragment
}