package com.example.a2022_q2_osovskoy.presentation

sealed class MainState {
    class Loading(val remoteString: String = "", val localString: String = "") : MainState()
    class Success(val remoteString: String, val localString: String) : MainState()
}