package com.example.a2022_q2_osovskoy.domain.repository

interface TimerRepository {

    fun start(timerCallBack : (timeFormatted: String) -> Unit)

    fun shutdown()

    fun stop()

    fun destroy()
}