package com.example.a2022_q2_osovskoy.di

import com.example.a2022_q2_osovskoy.data.di.DataBindsModule
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.domain.di.DomainBindsModule
import dagger.Component

//todo обернуть в интерфейс

@Component(modules = [DataBindsModule::class, DomainBindsModule::class])
@AppScope
interface AppComponent {

    fun getMainActivityComponent(): MainActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}
