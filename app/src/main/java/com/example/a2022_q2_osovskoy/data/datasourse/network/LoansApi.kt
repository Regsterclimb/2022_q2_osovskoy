package com.example.a2022_q2_osovskoy.data.datasourse.network

import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanConditionResponse
import com.example.a2022_q2_osovskoy.data.datasourse.network.model.LoanResponse
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoansApi {

    @POST("/loans")
    suspend fun request(@Body loanRequest: LoanRequest): LoanResponse

    @GET("/loans/all")
    suspend fun getAll(): List<LoanResponse>

    @GET("/loans/{id}")
    suspend fun getLoanById(@Path("id") loanId: Long): LoanResponse

    @GET("/loans/conditions")
    suspend fun getLoanCondition(): LoanConditionResponse
}