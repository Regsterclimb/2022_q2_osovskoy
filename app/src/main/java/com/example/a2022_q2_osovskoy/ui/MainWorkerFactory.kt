package com.example.a2022_q2_osovskoy.ui

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.a2022_q2_osovskoy.presentation.ChildWorkerFactory
import javax.inject.Inject
import javax.inject.Provider


class MainWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out CoroutineWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): CoroutineWorker? {
        val foundEntry = workerFactories.entries
            .find { Class.forName(workerClassName).isAssignableFrom(it.key) } ?: return null

        return foundEntry.value.get().create(appContext, workerParameters)
    }
}