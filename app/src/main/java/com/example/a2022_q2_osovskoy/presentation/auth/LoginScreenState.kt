package com.example.a2022_q2_osovskoy.presentation.auth

sealed class LoginScreenState {
    object UnAuthorized : LoginScreenState()
    object Registration : LoginScreenState()
}
