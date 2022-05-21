package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.di.annotation.AppScope
import com.example.a2022_q2_osovskoy.di.data.DataBindsModule
import com.example.a2022_q2_osovskoy.di.domain.DomainBindsModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [
    DataBindsModule::class,
    DomainBindsModule::class,
    AndroidInjectionModule::class,
    ActivityModule::class
])
@AppScope
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}
