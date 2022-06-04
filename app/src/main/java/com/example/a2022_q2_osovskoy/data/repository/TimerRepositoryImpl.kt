package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasource.Timer
import com.example.a2022_q2_osovskoy.domain.repository.TimerRepository
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(private val timer: Timer) :
    TimerRepository {

    override fun start(timerCallBack: (time: String) -> Unit) {
        timer.start(timerCallBack)
    }

    override fun shutdown() {
        timer.shutDawn()
    }

    override fun stop() {
        timer.stop()
    }

    override fun destroy() {
        timer.onDestroy()
    }
}