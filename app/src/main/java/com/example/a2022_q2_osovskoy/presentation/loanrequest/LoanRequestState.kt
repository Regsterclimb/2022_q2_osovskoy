package com.example.a2022_q2_osovskoy.presentation.loanrequest

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

sealed class LoanRequestState {

    data class Success(val loan: Loan) : LoanRequestState()
    object Loading : LoanRequestState()
    object Typing : LoanRequestState()

    sealed class Error : LoanRequestState() {
        object BadRequest : Error()
        object Unauthorized : Error()
        object Forbidden : Error()
        object NotFound : Error()
        object ServerIsNotResponding : Error()
        object NoInternetConnection : Error()
        object Unknown : Error()
    }

    sealed class InputError : LoanRequestState() {
        object Name : InputError()
        object LastName : InputError()
        object Phone : InputError()
    }
}