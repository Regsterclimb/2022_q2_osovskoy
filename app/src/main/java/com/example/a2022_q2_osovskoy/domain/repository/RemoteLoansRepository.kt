package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition

interface RemoteLoansRepository {

    suspend fun requestLoan(loanRequest: LoanRequest): Loan

    suspend fun getAll(): List<Loan>

    suspend fun getLoanById(loanId: Long): Loan

    suspend fun getLoanCondition(): LoanCondition
}