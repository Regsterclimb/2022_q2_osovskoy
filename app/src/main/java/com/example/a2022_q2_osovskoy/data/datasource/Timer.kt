package com.example.a2022_q2_osovskoy.data.datasource

interface Timer {

    fun shutDawn()

    fun start(getProgress: (time: Int) -> Unit)

    fun stop()
}