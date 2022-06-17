package com.example.a2022_q2_osovskoy.presentation.loandetail

import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail

sealed class LoanDetailState {
    data class Approved(val isApproved: Boolean) : LoanDetailState()
    object Loading : LoanDetailState()

    sealed class Success : LoanDetailState() {
        data class Local(val localLoanDetail: LoanDetail) : Success()
        data class Remote(val remoteLoanDetail: LoanDetail) : Success()
    }

    sealed class Error : LoanDetailState() {
        object BadRequest : Error()
        object Unauthorized : Error()
        object Forbidden : Error()
        object NotFound : Error()
        object ServerNotResponding : Error()
        object NoInternetConnection : Error()
        object Unknown : Error()
    }
}