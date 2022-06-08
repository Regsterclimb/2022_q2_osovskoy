package com.example.a2022_q2_osovskoy.domain.entity

sealed class AuthResult {
    sealed class Registration : AuthResult() {
        object Success : Registration()
        object Error : Registration()
    }

    sealed class Login : AuthResult() {
        object Success : Registration()
        object Error : Registration()
    }
}
