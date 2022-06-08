package com.example.a2022_q2_osovskoy.presentation.auth

sealed class AuthState {
    sealed class Registration : AuthState() {
        object Success : Registration()
        object Error : Registration()
    }

    sealed class Login : AuthState() {
        object Success : Registration()
        object Error : Registration()
    }

    sealed class InputError : AuthState() {
        object Name : InputError()
        object Password : InputError()
    }
}