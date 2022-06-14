package com.example.a2022_q2_osovskoy.data.datasourse.local

import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity

interface LoansLocalDataSource {

    suspend fun insertLoan(loanEntity: LoanEntity)

    suspend fun insertAll(loansList: List<LoanEntity>)

    suspend fun getAll(): List<LoanEntity>

    suspend fun getById(loanId: Long): LoanEntity
}