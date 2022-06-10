package com.example.a2022_q2_osovskoy.presentation.loanrequest

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

sealed class LoanRequestState {

    class Success(val loan: Loan) : LoanRequestState()
    object Error : LoanRequestState()

    sealed class InputError : LoanRequestState() {
        object Name : InputError()
        object LastName : InputError()
        object Phone : InputError()
        object Amount : InputError()
    }
}