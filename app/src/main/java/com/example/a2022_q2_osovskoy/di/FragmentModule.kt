package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.ui.SplashFragment
import com.example.a2022_q2_osovskoy.ui.auth.AuthFragment
import com.example.a2022_q2_osovskoy.ui.loandetail.LoanDetailFragment
import com.example.a2022_q2_osovskoy.ui.loanhistory.LoanHistoryFragment
import com.example.a2022_q2_osovskoy.ui.loanrequest.LoanConditionFragment
import com.example.a2022_q2_osovskoy.ui.loanrequest.LoanRequestFragment
import com.example.a2022_q2_osovskoy.ui.loanrequest.LoanSuccessFragment
import com.example.a2022_q2_osovskoy.ui.registration.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun provideLoginFragment(): AuthFragment

    @ContributesAndroidInjector
    fun provideMainFragment(): LoanConditionFragment

    @ContributesAndroidInjector
    fun provideLoanRequestFragment(): LoanRequestFragment

    @ContributesAndroidInjector
    fun provideLoanHistoryFragment(): LoanHistoryFragment

    @ContributesAndroidInjector
    fun provideLoanDetailFragment(): LoanDetailFragment

    @ContributesAndroidInjector
    fun provideSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    fun provideRegistrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    fun provideLoanSuccessFragment(): LoanSuccessFragment
}