package com.example.a2022_q2_osovskoy.di.presentation

import com.example.a2022_q2_osovskoy.di.annotations.ShiftFragmentScope
import com.example.a2022_q2_osovskoy.ui.main_screen.ShiftFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface ShiftFragmentModule {

    @ContributesAndroidInjector
    @ShiftFragmentScope
    fun injectShiftFragment(): ShiftFragment
}
