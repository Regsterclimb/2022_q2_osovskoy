package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.ui.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DataModule::class,
    DomainModule::class,
    ActivityModule::class
]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}