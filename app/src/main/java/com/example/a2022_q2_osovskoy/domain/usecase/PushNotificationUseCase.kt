package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.WorkerRepository
import javax.inject.Inject

class PushNotificationUseCase @Inject constructor(private val workerRepository: WorkerRepository) {

    operator fun invoke(string: String) {
        workerRepository.pushNotification(string)
    }
}