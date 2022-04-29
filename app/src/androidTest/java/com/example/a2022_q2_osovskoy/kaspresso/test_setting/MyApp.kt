package com.example.a2022_q2_osovskoy.kaspresso.test_setting

import com.example.a2022_q2_osovskoy.presentation.App
import kotlinx.coroutines.Dispatchers

class MyApp : App() {
    companion object {
        private const val LOCAL_HOST_URL = "http://127.0.0.1:8080"
        private val DISPATCHER = Dispatchers.Main
    }

    override fun getBaseUrl(): String {
        return LOCAL_HOST_URL
    }

    override fun getDispatcherMainImmediate() = DISPATCHER
    override fun getDispatcherDefault() = DISPATCHER
    override fun getDispatcherIO() = DISPATCHER
}