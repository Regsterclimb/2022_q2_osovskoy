package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasource.Timer
import com.example.a2022_q2_osovskoy.domain.repository.MainRepository

class MainRepositoryImpl(private val timer: Timer) : MainRepository {

    override fun getTimerProgress(block: (int: Int) -> Unit) {
    }

    override fun startTimer() {
        timer.start()
    }
}