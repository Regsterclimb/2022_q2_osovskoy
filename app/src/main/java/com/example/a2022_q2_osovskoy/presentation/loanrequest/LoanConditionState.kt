package com.example.a2022_q2_osovskoy.presentation.loanrequest

import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition

sealed class LoanConditionState {
    data class Success(val loanCondition: LoanCondition) : LoanConditionState()
    object Error : LoanConditionState()
}