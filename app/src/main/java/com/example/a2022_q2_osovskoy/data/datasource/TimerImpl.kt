package com.example.a2022_q2_osovskoy.data.datasource

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class TimerImpl(private val getProgress: (time: Int) -> Unit) : Timer {

    private var time = AtomicInteger(0)

    private val timerExecutor = Executors.newSingleThreadScheduledExecutor()

    override fun start() {
        timerExecutor.scheduleAtFixedRate(getTimerWork(), 100, 100, TimeUnit.MILLISECONDS)
    }

    private fun getTimerWork(): Runnable = Runnable {
        getProgress(time.getAndIncrement())
    }

    override fun stop() {
        timerExecutor.shutdown()
    }
}