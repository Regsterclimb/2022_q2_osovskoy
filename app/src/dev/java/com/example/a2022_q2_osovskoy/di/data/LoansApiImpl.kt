package com.example.a2022_q2_osovskoy.di.data

import com.example.a2022_q2_osovskoy.data.datasourse.network.LoansApi
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import retrofit2.mock.BehaviorDelegate

class LoansApiImpl(private val delegate: BehaviorDelegate<LoansApi>) : LoansApi {

    override suspend fun request(loanRequest: LoanRequest): LoanResponse {
        val response = LoanResponse(
            "24.12.14T.123",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "1234838",
            145,
            "APPROVED",
            8.5)

        return delegate.returningResponse(response).request(loanRequest)
    }

    override suspend fun getAll(): List<LoanResponse> {
        val responseList = getLoanResponseList()

        return delegate.returningResponse(responseList).getAll()
    }

    override suspend fun getLoanById(loanId: Long): LoanResponse {
        val response = LoanResponse(
            "24.12.14T.123",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            144,
            "APPROVED",
            8.5)

        return delegate.returningResponse(response).getLoanById(145)
    }

    override suspend fun getLoanCondition(): LoanConditionResponse {
        val loanConditionResponse = LoanConditionResponse(45, 15000, 8.5)

        return delegate.returningResponse(loanConditionResponse).getLoanCondition()
    }

    private fun getLoanResponseList(): List<LoanResponse> = listOf(
        LoanResponse(
            "24.12.14T.123",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "+1238493",
            144,
            "APPROVED",
            8.5),
        LoanResponse(
            "24.12.14T.123",
            "Олег",
            "Lorem",
            15000.0,
            45,
            "88005553535",
            145,
            "REGISTERED",
            8.5),
        LoanResponse(
            "24.12.14T.123",
            "Lorem",
            "Olegovich",
            15000.0,
            45,
            "880088003535",
            146,
            "REJECTED",
            8.5)
    )
}