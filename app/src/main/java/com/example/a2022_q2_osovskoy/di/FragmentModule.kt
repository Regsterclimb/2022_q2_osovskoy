package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun provideLoginFragment(): LoginFragment
}