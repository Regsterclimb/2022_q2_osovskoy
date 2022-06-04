package com.example.a2022_q2_osovskoy.data.datasource

import com.example.a2022_q2_osovskoy.extentions.toMyTimerFormat
import java.util.concurrent.Future
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject


class ExecutorServiceTimer @Inject constructor(
    private var timerScheduledExecutor: ScheduledExecutorService,
) : Timer {

    private var timerSeconds = AtomicInteger(INITIAL_VALUE)

    private var timerFuture: Future<*>? = null

    override fun start(getTime: (time: String) -> Unit) {
        val work = Runnable {
            getTime(timerSeconds.getAndIncrement().toMyTimerFormat())
        }

        timerFuture = timerScheduledExecutor.scheduleAtFixedRate(
            work, INITIAL_DELAY, PERIOD, TimeUnit.MILLISECONDS
        )
        timerScheduledExecutor.schedule((::interruptTimer), STOP_DELAY, TimeUnit.HOURS)
    }

    private fun interruptTimer() {
        timerFuture?.cancel(true)
    }

    override fun stop() {
        interruptTimer()
    }

    override fun shutDawn() {
        interruptTimer()
        timerSeconds.set(INITIAL_VALUE)
    }

    override fun onDestroy() {
        timerFuture = null
    }

    companion object {
        const val INITIAL_VALUE = 0
        const val INITIAL_DELAY = 100L
        const val PERIOD = 100L
        const val STOP_DELAY = 5L
    }
}