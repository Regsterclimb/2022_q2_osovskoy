package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasource.Timer
import com.example.a2022_q2_osovskoy.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val timer: Timer) :
    MainRepository {

    override fun startTimer(timerCallBack: (seconds: Int) -> Unit) {
        timer.start(timerCallBack)
    }

    override fun shutdownTimer() {
        timer.shutDawn()
    }

    override fun stopTimer() {
        timer.stop()
    }
}