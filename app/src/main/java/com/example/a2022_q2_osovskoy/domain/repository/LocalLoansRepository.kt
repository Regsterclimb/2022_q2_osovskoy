package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

interface LocalLoansRepository {

    suspend fun getAll() : List<Loan>
    //todo()
    suspend fun getById(loanId: Long) : Loan
}