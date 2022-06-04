package com.example.a2022_q2_osovskoy.di.ui

import com.example.a2022_q2_osovskoy.di.annotations.WorkerKey
import com.example.a2022_q2_osovskoy.ui.worker.ChildWorkerFactory
import com.example.a2022_q2_osovskoy.ui.worker.NotificationWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NotificationBuilderModule::class])
interface UiModule {

    @Binds
    @[IntoMap WorkerKey(NotificationWorker::class)]
    fun bindMyWorker(notificationWorker: NotificationWorker.Factory): ChildWorkerFactory
}