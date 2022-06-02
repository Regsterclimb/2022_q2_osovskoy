package com.example.a2022_q2_osovskoy.di.ui

import com.example.a2022_q2_osovskoy.di.annotations.WorkerKey
import com.example.a2022_q2_osovskoy.presentation.ChildWorkerFactory
import com.example.a2022_q2_osovskoy.ui.MyWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NotificationBuilderModule::class])
interface UiModule {

    @Binds
    @[IntoMap WorkerKey(MyWorker::class)]
    fun bindMyWorker(myWorker: MyWorker.Factory): ChildWorkerFactory
}