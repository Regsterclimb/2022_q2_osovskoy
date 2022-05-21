package com.example.a2022_q2_osovskoy.di.presentation

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotations.ViewModelKey
import com.example.a2022_q2_osovskoy.di.ui.MainActivityModule
import com.example.a2022_q2_osovskoy.presentation.second_screen.SecondScreenViewModel
import com.example.a2022_q2_osovskoy.presentation.viewmodel.ItemsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [MainActivityModule::class])
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(SecondScreenViewModel::class)]
    fun bindSecondScreenViewModel(secondScreenViewModel: SecondScreenViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ItemsViewModel::class)]
    fun bindItemsViewModel(itemsViewModel: ItemsViewModel): ViewModel
}