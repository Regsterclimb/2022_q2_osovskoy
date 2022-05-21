package com.example.a2022_q2_osovskoy.di.presentation

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotation.ViewModelKey
import com.example.a2022_q2_osovskoy.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationBindsModule {

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

}