package com.example.a2022_q2_osovskoy.domain.entity

sealed class LoginState {
    object Success : LoginState()
    object Error : LoginState()
}
