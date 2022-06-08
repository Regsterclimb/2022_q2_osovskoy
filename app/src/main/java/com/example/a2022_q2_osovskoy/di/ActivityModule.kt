package com.example.a2022_q2_osovskoy.di

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotations.ViewModelKey
import com.example.a2022_q2_osovskoy.di.presentation.PresentationModule
import com.example.a2022_q2_osovskoy.presentation.SupportViewModel
import com.example.a2022_q2_osovskoy.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class, PresentationModule::class])
    fun provideMainActivity(): MainActivity

    @Binds
    @[IntoMap ViewModelKey(SupportViewModel::class)]
    fun bindMainViewModel(supportViewModel: SupportViewModel): ViewModel
}