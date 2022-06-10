package com.example.a2022_q2_osovskoy.presentation.auth

sealed class AuthState {
    object Success : AuthState()
    object Error : AuthState()
    object Loading  : AuthState()


    sealed class InputError : AuthState() {
        object Name : InputError()
        object Password : InputError()
    }
}