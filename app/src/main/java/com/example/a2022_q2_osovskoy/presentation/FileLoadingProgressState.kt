package com.example.a2022_q2_osovskoy.presentation

sealed class FileLoadingProgressState {
    class Loading(val value: Int) : FileLoadingProgressState()
    object Success : FileLoadingProgressState()
    object Error : FileLoadingProgressState()
}