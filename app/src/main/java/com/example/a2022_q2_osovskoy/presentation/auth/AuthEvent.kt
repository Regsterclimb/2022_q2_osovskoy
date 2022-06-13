package com.example.a2022_q2_osovskoy.presentation.auth

sealed class AuthEvent {
    object Success : AuthEvent()
    object Error : AuthEvent()
    object Loading  : AuthEvent()


    sealed class InputError : AuthEvent() {
        object Name : InputError()
        object Password : InputError()
    }
}