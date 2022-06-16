package com.example.a2022_q2_osovskoy.presentation.loanrequest

import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition

sealed class LoanConditionState {
    data class Success(val loanCondition: LoanCondition) : LoanConditionState()
    object Loading : LoanConditionState()

    sealed class Error : LoanConditionState() {
        object BadRequest : Error()
        object Unauthorized : Error()
        object Forbidden : Error()
        object NotFound : Error()
        object ServerIsNotResponding : Error()
        object NoInternetConnection : Error()
        object Unknown : Error()
    }
}