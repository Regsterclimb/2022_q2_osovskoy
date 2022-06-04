package com.example.a2022_q2_osovskoy.di.data

import android.content.Context
import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.data.datasource.ExecutorServiceTimer
import com.example.a2022_q2_osovskoy.data.datasource.Timer
import com.example.a2022_q2_osovskoy.data.datasource.worker.NotificationWorkerInitializer
import com.example.a2022_q2_osovskoy.data.datasource.worker.WorkerInitializer
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

@Module
interface DataModule {

    companion object {
        @Provides
        fun provideExecutorService(): ScheduledExecutorService =
            Executors.newSingleThreadScheduledExecutor()

        @Provides
        fun provideApplicationContext(app: App): Context = app.applicationContext
    }

    @Binds
    @AppScope
    fun bindTimer(executorService: ExecutorServiceTimer): Timer

    @Binds
    fun bindNotificationWorker(impl: NotificationWorkerInitializer): WorkerInitializer
}