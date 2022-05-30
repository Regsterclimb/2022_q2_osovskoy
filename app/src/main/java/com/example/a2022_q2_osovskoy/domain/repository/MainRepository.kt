package com.example.a2022_q2_osovskoy.domain.repository

interface MainRepository {

    fun getTimerProgress(block : (int: Int) -> Unit)

    fun startTimer()
}