package com.example.a2022_q2_osovskoy.presentation.loanhistory

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

sealed class LoansState {
    class Success(val loans: List<Loan>) : LoansState()
    object Error : LoansState()
    object Empty : LoansState()
    object Loading : LoansState()
}