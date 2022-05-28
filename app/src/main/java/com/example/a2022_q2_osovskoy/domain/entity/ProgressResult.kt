package com.example.a2022_q2_osovskoy.domain.entity

sealed class ProgressResult {
    class Loading(val progressValue: Int) : ProgressResult()
    object Success : ProgressResult()
    object Error : ProgressResult()
}