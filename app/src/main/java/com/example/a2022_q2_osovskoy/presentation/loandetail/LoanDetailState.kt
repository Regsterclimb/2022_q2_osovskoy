package com.example.a2022_q2_osovskoy.presentation.loandetail

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

sealed class LoanDetailState {
    class Success(val loanDetail: Loan) : LoanDetailState()
    object Error : LoanDetailState()
}