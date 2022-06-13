package com.example.a2022_q2_osovskoy.data.datasourse.remote

import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest

interface LoansDataSource {

    suspend fun requestLoan(loanRequest: LoanRequest): LoanResponse

    suspend fun getAll(): List<LoanResponse>

    suspend fun getLoanById(loanId: Long): LoanResponse

    suspend fun getLoanCondition(): LoanConditionResponse
}