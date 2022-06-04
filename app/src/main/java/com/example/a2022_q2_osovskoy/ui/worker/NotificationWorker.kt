package com.example.a2022_q2_osovskoy.ui.worker

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker.Result.success
import androidx.work.WorkerParameters
import com.example.a2022_q2_osovskoy.extentions.provideIntent
import com.example.a2022_q2_osovskoy.extentions.provideNotificationBuilder
import com.example.a2022_q2_osovskoy.extentions.providePendingIntent
import com.example.a2022_q2_osovskoy.ui.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val notificationBuilder: NotificationCompat.Builder,
) : CoroutineWorker(context, workerParameters) {

    companion object {
        const val NOTIFICATION_TITLE = "Your time is here"
        const val EMPTY_NOTIFICATION_TEXT = ""
        const val NOTIFICATION_ID = 2
    }

    override suspend fun doWork(): Result {
        val lastTime: String? = inputData.getString(MainActivity.DATA_ID)
        val myNotification = provideNotificationBuilder(
                applicationContext,
                providePendingIntent(applicationContext, provideIntent(applicationContext))
            ).apply {
                setContentTitle(NOTIFICATION_TITLE)
                setContentText(lastTime ?: EMPTY_NOTIFICATION_TEXT)
            }.build()

        NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID, myNotification)
        return success()
    }

    @AssistedFactory
    interface Factory : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): NotificationWorker
    }
}