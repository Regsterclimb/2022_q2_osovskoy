package com.example.a2022_q2_osovskoy.di

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.presentation.MainViewModel
import com.maxsch.rxjavalecture.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}