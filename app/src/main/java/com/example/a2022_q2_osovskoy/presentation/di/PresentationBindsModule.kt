package com.example.a2022_q2_osovskoy.presentation.di

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.MyFragmentComponent
import com.example.a2022_q2_osovskoy.presentation.view_model.MainViewModel
import com.example.a2022_q2_osovskoy.presentation.view_model.TestViewModel
import com.example.a2022_q2_osovskoy.presentation.view_model.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = [MyFragmentComponent::class])
interface PresentationBindsModule {

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    //todo()
    @Binds
    @[IntoMap ViewModelKey(TestViewModel::class)]
    fun provideTestViewModel(testViewModel: TestViewModel): ViewModel

}