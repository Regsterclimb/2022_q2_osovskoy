package com.example.a2022_q2_osovskoy.presentation.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TimerWatcher {

    fun observableData(): LiveData<String> = incData

    private var incData = MutableLiveData("00:00")

    private var outData = MutableLiveData("00:00")

    fun watchTimer(p0: Long) {
        val seconds: Long = p0 / 1000
        val time = String.format("%02d:%02d", seconds % 3600 / 60, seconds % 60)
        incData.value = time
    }

    fun finishTimer() {
        incData.value = "Finished"
    }

    fun registerTimer(isRegistered: Boolean) {
        if (!isRegistered) {
            incData = outData
        }
    }
}