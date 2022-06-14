package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.LoansLocalDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansDataSource
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.repository.LoansRepository
import com.example.a2022_q2_osovskoy.extentions.*
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoansRepositoryImpl @Inject constructor(
    private val loansDataSource: LoansDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val loansLocalDataSource: LoansLocalDataSource,
) : LoansRepository {

    override suspend fun requestLoan(loanRequest: LoanRequest): ResultState<Loan> =
        dispatcher.execute {
            loansDataSource.requestLoan(loanRequest).toLoan()
        }

    override suspend fun getAll(): ResultState<List<Loan>> = dispatcher.execute {
        if (hasInternetConnection(this.dispatcher)) {
            val list = loansDataSource.getAll()
            loansLocalDataSource.insertAll(list.map { it.toLoanEntity() })
            list.map { it.toLoan() }
        } else {
            loansLocalDataSource.getAll().map { it.toLoan() }
        }
    }

    override suspend fun getLoanById(loanId: Long): ResultState<Loan> = dispatcher.execute {
        if (hasInternetConnection(this.dispatcher)) {
            val loanResponse = loansDataSource.getLoanById(loanId)
            loansLocalDataSource.insertLoan(loanResponse.toLoanEntity())
            loanResponse.toLoan()
        } else {
            loansLocalDataSource.getById(loanId).toLoan()
        }
    }

    override suspend fun getLoanCondition(): ResultState<LoanCondition> = dispatcher.execute {
        loansDataSource.getLoanCondition().toLoanCondition()
    }
}