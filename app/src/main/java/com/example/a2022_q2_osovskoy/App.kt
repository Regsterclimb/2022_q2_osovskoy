package com.example.a2022_q2_osovskoy

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.a2022_q2_osovskoy.di.DaggerAppComponent
import com.example.a2022_q2_osovskoy.ui.MainWorkerFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    companion object {
        const val CHANNEL_NAME = "my_channel"
        const val CHANNEL_DESCRIPTION = "push Timer time"
        const val CHANNEL_ID = "123456"
    }

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var workerFactory: MainWorkerFactory

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(this).inject(this)

        createNotificationChannel()

        WorkManager.initialize(this,
            Configuration.Builder().setWorkerFactory(workerFactory).build())
    }

    override fun androidInjector(): AndroidInjector<Any> = injector

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}