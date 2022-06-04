package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.TimerRepository
import javax.inject.Inject

class ShutDawnTimerUseCase @Inject constructor(private val timerRepository: TimerRepository) {

    operator fun invoke() {
        timerRepository.shutdown()
    }
}