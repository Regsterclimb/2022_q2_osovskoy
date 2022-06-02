package com.example.a2022_q2_osovskoy.presentation

sealed class TimerState {
    class Working(val workingTime: String) : TimerState()
    class Stopped(val stoppedTime: String) : TimerState()
    object ShutDowned : TimerState()
    object Started : TimerState()
}