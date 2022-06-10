package com.example.a2022_q2_osovskoy.presentation.loanrequest

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

sealed class LoanState {
    class Success(val loan: Loan) : LoanState()
    object Error : LoanState()
}
