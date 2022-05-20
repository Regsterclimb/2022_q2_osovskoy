package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.di.data.DataModule
import com.example.a2022_q2_osovskoy.di.domain.DomainModule
import com.example.a2022_q2_osovskoy.di.presentation.PresentationModule
import com.example.a2022_q2_osovskoy.presentation.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    DataModule::class,
    DomainModule::class,
    PresentationModule::class,
    AndroidSupportInjectionModule::class,
])
@AppScope
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}