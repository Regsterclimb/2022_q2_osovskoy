package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.MainRepository
import javax.inject.Inject

class ShutDawnTimerUseCase @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke() = mainRepository.shutdownTimer()
}