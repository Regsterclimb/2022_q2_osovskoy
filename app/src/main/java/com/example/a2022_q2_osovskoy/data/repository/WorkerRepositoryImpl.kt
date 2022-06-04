package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasource.worker.WorkerInitializer
import com.example.a2022_q2_osovskoy.domain.repository.WorkerRepository
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(private val workerInitializer: WorkerInitializer) :
    WorkerRepository {

    override fun pushNotification(string: String) {
        workerInitializer.initialize(string)
    }
}