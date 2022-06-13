package com.example.a2022_q2_osovskoy.presentation.registration

sealed class RegEvent {
    object Success : RegEvent()
    object Error : RegEvent()
    object Loading : RegEvent()

    sealed class InputError : RegEvent() {
        object Name : InputError()
        object Password : InputError()
    }
}
