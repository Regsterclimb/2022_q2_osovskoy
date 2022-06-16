package com.example.a2022_q2_osovskoy.presentation.registration

sealed class RegState : Error() {
    object Success : RegState()
    object Typing : RegState()
    object Loading : RegState()

    sealed class Error : RegState() {
        object BadRequest : Error()
        object Unauthorized : Error()
        object Forbidden : Error()
        object NotFound : Error()
        object ServerIsNotResponding : Error()
        object NoInternetConnection : Error()
        object Unknown : Error()
    }

    sealed class InputError : RegState() {
        object Name : InputError()
        object Password : InputError()
    }
}
