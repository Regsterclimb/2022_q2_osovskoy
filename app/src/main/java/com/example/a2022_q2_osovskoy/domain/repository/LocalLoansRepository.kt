package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail

interface LocalLoansRepository {

    suspend fun getAll() : List<Loan>

    suspend fun getById(loanId: Long) : LoanDetail
}