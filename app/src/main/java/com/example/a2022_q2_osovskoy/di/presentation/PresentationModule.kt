package com.example.a2022_q2_osovskoy.di.presentation

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotations.ViewModelKey
import com.example.a2022_q2_osovskoy.presentation.auth.AuthViewModel
import com.example.a2022_q2_osovskoy.presentation.history.LoansHistoryViewModel
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanRequestViewModel
import com.example.a2022_q2_osovskoy.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(AuthViewModel::class)]
    fun bindLoginViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LoanRequestViewModel::class)]
    fun bindRequestViewModel(loanRequestViewModel: LoanRequestViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LoansHistoryViewModel::class)]
    fun bindLoansHistoryViewModel(loansHistoryViewModel: LoansHistoryViewModel): ViewModel

}