package com.example.a2022_q2_osovskoy.presentation.loanrequest

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition

sealed class LoanRequestEvent {

    data class Success(val loan: Loan) : LoanRequestEvent()
    data class LoanConditionReceived(val loanCondition: LoanCondition) : LoanRequestEvent()
    object Error : LoanRequestEvent()
    object Loading : LoanRequestEvent()

    sealed class InputError : LoanRequestEvent() {
        object Name : InputError()
        object LastName : InputError()
        object Phone : InputError()
    }
}