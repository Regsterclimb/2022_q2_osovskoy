package com.example.a2022_q2_osovskoy.ui

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker.Result.success
import androidx.work.WorkerParameters
import com.example.a2022_q2_osovskoy.presentation.ChildWorkerFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class MyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val notificationBuilder: NotificationCompat.Builder,
) : CoroutineWorker(context, workerParameters) {

    companion object {
        const val NOTIFICATION_TITLE = "Here is your time"
        const val EMPTY_NOTIFICATION_TEXT = ""
    }

    override suspend fun doWork(): Result {
        val lastTime: String? = inputData.getString(MainActivity.DATA_ID)
        val myNotification = notificationBuilder.apply {
            setContentTitle(NOTIFICATION_TITLE)
            setContentText(lastTime ?: EMPTY_NOTIFICATION_TEXT)
        }.build()

        NotificationManagerCompat.from(applicationContext).notify(1, myNotification)
        return success()
    }

    @AssistedFactory
    interface Factory : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): MyWorker
    }
}