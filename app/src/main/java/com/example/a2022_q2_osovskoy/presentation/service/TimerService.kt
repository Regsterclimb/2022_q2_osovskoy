package com.example.a2022_q2_osovskoy.presentation.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import androidx.lifecycle.LiveData


class TimerService : Service() {

    private val binder = TimerBinder()

    private var timerWatcher: TimerWatcher? = null

    private var countDownTimer: CountDownTimer? = null

    inner class TimerBinder : Binder() {
        fun getService(): TimerService {
            return this@TimerService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        timerWatcher?.registerTimer(true)
        return binder
    }

    override fun onRebind(intent: Intent?) {
        timerWatcher?.registerTimer(true)
    }

    override fun onCreate() {
        timerWatcher = TimerWatcher()

        countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                timerWatcher?.watchTimer(p0)
            }

            override fun onFinish() {
                timerWatcher?.finishTimer()
            }
        }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        countDownTimer?.start()
        return START_NOT_STICKY
    }

    override fun onUnbind(intent: Intent?): Boolean {
        timerWatcher?.registerTimer(false)
        return super.onUnbind(intent)
    }

    fun observeTime(): LiveData<String>? = timerWatcher?.observableData()

    override fun onDestroy() {
        countDownTimer?.cancel()
        countDownTimer = null
        timerWatcher = null
        super.onDestroy()
    }
}