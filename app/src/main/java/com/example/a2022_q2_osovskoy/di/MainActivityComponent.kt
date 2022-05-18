package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.presentation.di.PresentationBindsModule
import com.example.a2022_q2_osovskoy.presentation.view_model.MultiViewModelFactory
import dagger.Subcomponent

@Subcomponent(modules = [PresentationBindsModule::class])
interface MainActivityComponent {

    val multiModelFactory: MultiViewModelFactory

    fun getMyFragmentComponent(): MyFragmentComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }
}
