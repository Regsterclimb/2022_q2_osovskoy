package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.MainRepository

class StartTimerUseCase(private val mainRepository: MainRepository) {

    operator fun invoke() = mainRepository.startTimer()
}