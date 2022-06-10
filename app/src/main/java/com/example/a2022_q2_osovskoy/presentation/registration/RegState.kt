package com.example.a2022_q2_osovskoy.presentation.registration

sealed class RegState {
    object Success : RegState()
    object Error : RegState()

    sealed class InputError : RegState() {
        object Name : InputError()
        object Password : InputError()
    }
}
