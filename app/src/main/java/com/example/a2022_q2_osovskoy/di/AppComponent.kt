package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.di.data.DataModule
import com.example.a2022_q2_osovskoy.di.domain.DomainModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DataModule::class,
    DomainModule::class,
    ActivityModule::class])
@AppScope
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}