package com.example.a2022_q2_osovskoy.data.datasource.worker

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.a2022_q2_osovskoy.ui.MainActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NotificationWorkerInitializer @Inject constructor(private val context: Context) :
    WorkerInitializer {

    override fun initialize(lastTime: String) {
        WorkManager.getInstance(context).enqueue(createOneTimePush(lastTime))
    }

    private fun createOneTimePush(string: String): OneTimeWorkRequest =
        OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInputData(Data.Builder().putString(MainActivity.DATA_ID, string).build())
            .setInitialDelay(INITIAL_DELAY, TimeUnit.SECONDS)
            .build()

    companion object {
        private const val INITIAL_DELAY = 5L
    }
}