package com.example.a2022_q2_osovskoy.data.datasource

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject


class TimerImpl @Inject constructor(private var timerScheduledExecutor: ScheduledExecutorService) :
    Timer {

    companion object {
        const val INITIAL_VALUE = 0
        const val INITIAL_DELAY = 100L
        const val PERIOD = 100L
    }

    private var timerSeconds = AtomicInteger(INITIAL_VALUE)

    //todo() добавить остановку после часа
    override fun start(getProgress: (seconds: Int) -> Unit) {
        if (timerScheduledExecutor.isTerminated) {
            timerScheduledExecutor = Executors.newSingleThreadScheduledExecutor()
        }

        timerScheduledExecutor.scheduleAtFixedRate(
            {
                getProgress(timerSeconds.getAndIncrement())
            },
            INITIAL_DELAY,
            PERIOD,
            TimeUnit.MILLISECONDS)
    }

    override fun stop() {
        if (!timerScheduledExecutor.isTerminated) {
            timerScheduledExecutor.shutdownNow()
        }
    }

    override fun shutDawn() {
        if (!timerScheduledExecutor.isTerminated) {
            timerScheduledExecutor.shutdownNow()
        }
        timerSeconds.set(INITIAL_VALUE)
    }
}