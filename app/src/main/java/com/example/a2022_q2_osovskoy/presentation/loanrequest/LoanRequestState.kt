package com.example.a2022_q2_osovskoy.presentation.loanrequest

sealed class LoanRequestState {

    object Success : LoanRequestState()

    sealed class InputError : LoanRequestState() {
        object Name : InputError()
        object LastName : InputError()
        object Phone : InputError()
        object Amount : InputError()
    }
}