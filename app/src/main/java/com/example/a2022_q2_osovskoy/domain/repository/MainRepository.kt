package com.example.a2022_q2_osovskoy.domain.repository

interface MainRepository {

    fun startTimer(timerCallBack : (seconds: Int) -> Unit)

    fun shutdownTimer()

    fun stopTimer()
}