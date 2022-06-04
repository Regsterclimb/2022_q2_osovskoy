package com.example.a2022_q2_osovskoy.data.datasource

interface Timer {

    fun shutDawn()

    fun start(getTime: (time: String) -> Unit)

    fun stop()

    fun onDestroy()
}