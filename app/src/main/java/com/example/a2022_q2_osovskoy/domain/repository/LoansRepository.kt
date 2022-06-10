package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition

interface LoansRepository {

    suspend fun requestLoan(loanRequest: LoanRequest): ResultState<Loan>

    suspend fun getAll(): ResultState<List<Loan>>

    suspend fun getLoanById(loanId: Long): ResultState<Loan>

    suspend fun getLoanCondition(): ResultState<LoanCondition>
}