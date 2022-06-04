package com.example.a2022_q2_osovskoy.presentation

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.domain.usecase.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val startTimerUseCase: StartTimerUseCase,
    private val shutDawnTimerUseCase: ShutDawnTimerUseCase,
    private val stopTimerUseCase: StopTimerUseCase,
    private val destroyTimerUseCase: DestroyTimerUseCase,
    private val pushNotificationUseCase: PushNotificationUseCase,
    private val handler: Handler,
) : ViewModel() {

    private val _timerState = MutableLiveData<TimerState>(TimerState.ShutDowned)
    val timerState = _timerState

    private val _timerValue = MutableLiveData("00:00")
    val timerValue = _timerValue

    fun startTimer() {
        startTimerUseCase { resultTime ->
            handler.post {
                _timerState.value = TimerState.Working(resultTime)
                _timerValue.value = resultTime
            }
        }
    }

    fun shutDawnTimer() {
        _timerState.value = TimerState.ShutDowned
        shutDawnTimerUseCase()
    }

    fun stopTimer(time: String) {
        _timerState.value = TimerState.Stopped(time)
        stopTimerUseCase()
    }

    fun destroyTimer() {
        destroyTimerUseCase()
    }

    fun pushNotification(time: String) {
        pushNotificationUseCase(time)
    }
}