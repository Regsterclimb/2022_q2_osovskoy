package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.LoansLocalDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansDataSource
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import com.example.a2022_q2_osovskoy.domain.repository.RemoteLoansRepository
import com.example.a2022_q2_osovskoy.extentions.*
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteLoansRepositoryImpl @Inject constructor(
    private val loansDataSource: LoansDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val loansLocalDataSource: LoansLocalDataSource,
) : RemoteLoansRepository {

    override suspend fun requestLoan(loanRequest: LoanRequest): Loan = dispatcher.execute {
        loansDataSource.requestLoan(loanRequest).toLoan()
    }

    override suspend fun getAll(): List<Loan> = dispatcher.execute {
        val loansList = loansDataSource.getAll()
        loansLocalDataSource.insertAll(loansList.map { it.toLoanEntity() })
        loansList.map { it.toLoan() }
    }

    override suspend fun getLoanById(loanId: Long): LoanDetail = dispatcher.execute {
        val loanResponse = loansDataSource.getLoanById(loanId)
        loansLocalDataSource.insertLoan(loanResponse.toLoanEntity())
        loanResponse.toLoanDetails()
    }

    override suspend fun getLoanCondition(): LoanCondition = dispatcher.execute {
        loansDataSource.getLoanCondition().toLoanCondition()
    }
}