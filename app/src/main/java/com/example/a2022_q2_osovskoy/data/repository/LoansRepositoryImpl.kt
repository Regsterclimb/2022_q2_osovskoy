package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansDataSource
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.repository.LoansRepository
import com.example.a2022_q2_osovskoy.extentions.toLoan
import com.example.a2022_q2_osovskoy.extentions.toLoanCondition
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoansRepositoryImpl @Inject constructor(
    private val loansDataSource: LoansDataSource,
    private val dispatcher: CoroutineDispatcher,
) :
    LoansRepository {
    override suspend fun requestLoan(loanRequest: LoanRequest): Loan =
        loansDataSource.requestLoan(loanRequest).toLoan()

    override suspend fun getAll(): List<Loan> =
        loansDataSource.getAll().map { loanResponse -> loanResponse.toLoan() }

    override suspend fun getLoanById(loanId: Long): Loan =
        loansDataSource.getLoanById(loanId).toLoan()

    override suspend fun getLoanCondition(): LoanCondition = withContext(dispatcher) {
        loansDataSource.getLoanCondition().toLoanCondition()
    }
}