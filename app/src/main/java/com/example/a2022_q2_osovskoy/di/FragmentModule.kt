package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.di.annotation.MyFragmentScope
import com.example.a2022_q2_osovskoy.presentation.MyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    @MyFragmentScope
    fun contributeMyFragment(): MyFragment
}