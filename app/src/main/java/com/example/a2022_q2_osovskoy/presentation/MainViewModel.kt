package com.example.a2022_q2_osovskoy.presentation

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2022_q2_osovskoy.domain.usecase.ShutDawnTimerUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.StartTimerUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.StopTimerUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val startTimerUseCase: StartTimerUseCase,
    private val shutDawnTimerUseCase: ShutDawnTimerUseCase,
    private val stopTimerUseCase: StopTimerUseCase,
    private val handler: Handler,
) : ViewModel() {

    private val _timerState = MutableLiveData<TimerState>(TimerState.ShutDowned)
    val timerState = _timerState

    private val _timerValue = MutableLiveData("00:00")
    val timerValue = _timerValue

    fun startTimer() {
        _timerState.value = TimerState.Started
        startTimerUseCase {
            handler.post {
                _timerState.value = TimerState.Working(
                    workingTime = String.format("%02d:%02d", it % 3600 / 60, it % 60)
                )
                _timerValue.value = String.format("%02d:%02d", it % 3600 / 60, it % 60)
            }
        }
    }

    fun shutDawnTimer() {
        _timerState.value = TimerState.ShutDowned
        _timerValue.value = "00:00"
        shutDawnTimerUseCase()
    }

    fun stopTimer(time: String) {
        _timerState.value = TimerState.Stopped(stoppedTime = time)
        stopTimerUseCase()
    }
}