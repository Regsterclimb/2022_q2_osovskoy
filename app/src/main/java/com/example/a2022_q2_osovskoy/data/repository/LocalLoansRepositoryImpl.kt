package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.LoansLocalDataSource
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.repository.LocalLoansRepository
import com.example.a2022_q2_osovskoy.extentions.execute
import com.example.a2022_q2_osovskoy.extentions.toLoan
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LocalLoansRepositoryImpl @Inject constructor(
    private val localDataSource: LoansLocalDataSource,
    private val dispatcher: CoroutineDispatcher,
) :
    LocalLoansRepository {
    override suspend fun getAll(): List<Loan> = dispatcher.execute {
        localDataSource.getAll().map { it.toLoan() }
    }

    override suspend fun getById(loanId: Long): Loan = dispatcher.execute {
        localDataSource.getById(loanId).toLoan()
    }
}