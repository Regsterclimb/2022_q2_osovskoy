package com.example.a2022_q2_osovskoy.di

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.presentation.MainViewModel
import com.maxsch.rxjavalecture.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineExceptionHandler

@Module
interface PresentationModule {

    companion object {
        @Provides
        fun provideCoroutineExceptionHandler() : CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            Log.e("MainViewModel", "Failed to load animals and prices", exception)
        }
    }

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}