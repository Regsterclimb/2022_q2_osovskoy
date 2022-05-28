package com.example.a2022_q2_osovskoy.di.presentation

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotations.ViewModelKey
import com.example.a2022_q2_osovskoy.presentation.UploadScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(UploadScreenViewModel::class)]
    fun bindUploadViewModel(uploadScreenViewModel: UploadScreenViewModel): ViewModel
}