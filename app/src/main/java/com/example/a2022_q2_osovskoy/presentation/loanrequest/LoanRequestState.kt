package com.example.a2022_q2_osovskoy.presentation.loanrequest

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition

sealed class LoanRequestState {

    class Success(val loan: Loan) : LoanRequestState()
    class LoanConditionReceieved(val loanCondition: LoanCondition) : LoanRequestState()
    object Error : LoanRequestState()
    object Loading  : LoanRequestState()

    sealed class InputError : LoanRequestState() {
        object Name : InputError()
        object LastName : InputError()
        object Phone : InputError()
    }
}