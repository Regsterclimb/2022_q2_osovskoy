package com.example.a2022_q2_osovskoy.di.presentation

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotations.ViewModelKey
import com.example.a2022_q2_osovskoy.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    companion object {
        @Provides
        fun provideHandler(): Handler = Handler(Looper.getMainLooper())
    }

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}