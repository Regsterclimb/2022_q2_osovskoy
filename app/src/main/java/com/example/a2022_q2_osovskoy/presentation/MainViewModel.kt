package com.example.a2022_q2_osovskoy.presentation

import androidx.lifecycle.MutableLiveData
import com.example.a2022_q2_osovskoy.domain.usecase.StartTimerUseCase

class MainViewModel(private val startTimerUseCase: StartTimerUseCase) {

    private val _timerState = MutableLiveData<Int>()
    val timerState = _timerState

    fun startTimer() {
        startTimerUseCase()
    }
}