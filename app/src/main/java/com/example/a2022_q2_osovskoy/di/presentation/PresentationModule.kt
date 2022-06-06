package com.example.a2022_q2_osovskoy.di.presentation

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotations.ViewModelKey
import com.example.a2022_q2_osovskoy.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(LoginViewModel::class)]
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}