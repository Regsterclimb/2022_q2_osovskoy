package com.example.a2022_q2_osovskoy.presentation.loanhistory

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

sealed class LoansState {
    object Empty : LoansState()
    object Loading : LoansState()

    sealed class Success : LoansState() {
        data class Local(val localLoans:List<Loan>):Success()
        data class Remote(val remoteLoans:List<Loan>):Success()
    }

    sealed class Error : LoansState() {
        object BadRequest : Error()
        object Unauthorized : Error()
        object Forbidden : Error()
        object NotFound : Error()
        object ServerIsNotResponding : Error()
        object NoInternetConnection : Error()
        object Unknown : Error()
    }
}