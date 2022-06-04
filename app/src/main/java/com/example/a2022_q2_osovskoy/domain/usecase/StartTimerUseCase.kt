package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.TimerRepository
import javax.inject.Inject

class StartTimerUseCase @Inject constructor(private val timerRepository: TimerRepository) {

    operator fun invoke(timerCallBack: (timeFormatted: String) -> Unit) {
        timerRepository.start(timerCallBack)
    }
}