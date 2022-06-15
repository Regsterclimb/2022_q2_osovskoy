package com.example.a2022_q2_osovskoy.presentation.auth

sealed class AuthState {
    object Success : AuthState()
    object Loading : AuthState()
    object Typing : AuthState()

    sealed class Error : AuthState() {
        object BadRequest : Error()
        object Unauthorized : Error()
        object Forbidden : Error()
        object NotFound : Error()
        object ServerIsNotResponding : Error()
    }

    sealed class InputError : AuthState() {
        object Name : InputError()
        object Password : InputError()
    }
}