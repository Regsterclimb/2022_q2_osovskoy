package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.ui.auth.AuthFragment
import com.example.a2022_q2_osovskoy.ui.loanhistory.LoanHistoryFragment
import com.example.a2022_q2_osovskoy.ui.loanrequest.LoanRequestFragment
import com.example.a2022_q2_osovskoy.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun provideLoginFragment(): AuthFragment

    @ContributesAndroidInjector
    fun provideMainFragment(): MainFragment

    @ContributesAndroidInjector
    fun provideLoanRequestFragment(): LoanRequestFragment

    @ContributesAndroidInjector
    fun provideLoanHistoryFragment(): LoanHistoryFragment

}