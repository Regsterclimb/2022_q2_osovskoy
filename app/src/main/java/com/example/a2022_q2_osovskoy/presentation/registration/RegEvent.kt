package com.example.a2022_q2_osovskoy.presentation.registration

sealed class RegEvent {
    object Success : RegEvent()
    sealed class Error : RegEvent() {
        object BadRequest : Error()
    }
    object Loading : RegEvent()

    sealed class InputError : RegEvent() {
        object Name : InputError()
        object Password : InputError()
    }
}
