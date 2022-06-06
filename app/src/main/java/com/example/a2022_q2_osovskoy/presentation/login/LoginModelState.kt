package com.example.a2022_q2_osovskoy.presentation.login

sealed class LoginModelState {
    object Succeed : LoginModelState()
    object Error: LoginModelState()
}