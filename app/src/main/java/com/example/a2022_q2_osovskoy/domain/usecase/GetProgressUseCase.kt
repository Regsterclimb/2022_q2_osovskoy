package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.MainRepository

class GetProgressUseCase(private val repository: MainRepository) {

    operator fun invoke(block: (int: Int) -> Unit) = repository.getTimerProgress(block)
}