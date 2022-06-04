package com.example.a2022_q2_osovskoy.data.datasource.worker

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker.Result.success
import androidx.work.WorkerParameters
import com.example.a2022_q2_osovskoy.extentions.provideTimerNotification
import com.example.a2022_q2_osovskoy.ui.MainActivity


class NotificationWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    companion object {
        const val NOTIFICATION_TITLE = "Your time is here"
        const val EMPTY_NOTIFICATION_TEXT = ""
        const val NOTIFICATION_ID = 2
    }

    override suspend fun doWork(): Result {
        val myNotification =
            provideTimerNotification(applicationContext, inputData.getString(MainActivity.DATA_ID))
        NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID, myNotification)
        return success()
    }
}