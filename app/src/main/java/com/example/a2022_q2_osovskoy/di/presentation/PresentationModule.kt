package com.example.a2022_q2_osovskoy.di.presentation

import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.di.annotations.ViewModelKey
import com.example.a2022_q2_osovskoy.presentation.auth.AuthViewModel
import com.example.a2022_q2_osovskoy.presentation.availableloan.LoanConditionViewModel
import com.example.a2022_q2_osovskoy.presentation.history.LoansHistoryViewModel
import com.example.a2022_q2_osovskoy.presentation.loandetail.LoanDetailViewModel
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanRequestViewModel
import com.example.a2022_q2_osovskoy.presentation.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(AuthViewModel::class)]
    fun bindLoginViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LoanConditionViewModel::class)]
    fun bindMainViewModel(loanConditionViewModel: LoanConditionViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LoanRequestViewModel::class)]
    fun bindRequestViewModel(loanRequestViewModel: LoanRequestViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LoansHistoryViewModel::class)]
    fun bindLoansHistoryViewModel(loansHistoryViewModel: LoansHistoryViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LoanDetailViewModel::class)]
    fun bindLoanDetailViewModel(loanDetailViewModel: LoanDetailViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(RegistrationViewModel::class)]
    fun bindRegistrationViewModelViewModel(loanDetailViewModel: RegistrationViewModel): ViewModel

}