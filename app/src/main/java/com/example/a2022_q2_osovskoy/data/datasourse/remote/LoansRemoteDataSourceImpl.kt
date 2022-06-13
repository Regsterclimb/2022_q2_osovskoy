package com.example.a2022_q2_osovskoy.data.datasourse.remote

import com.example.a2022_q2_osovskoy.data.datasourse.network.LoansApi
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import javax.inject.Inject

class LoansRemoteDataSourceImpl @Inject constructor(private val loansApi: LoansApi) :
    LoansDataSource {

    override suspend fun requestLoan(loanRequest: LoanRequest): LoanResponse =
        loansApi.request(loanRequest)

    override suspend fun getAll(): List<LoanResponse> = loansApi.getAll()

    override suspend fun getLoanById(loanId: Long): LoanResponse = loansApi.getLoanById(loanId)

    override suspend fun getLoanCondition(): LoanConditionResponse = loansApi.getLoanCondition()

}