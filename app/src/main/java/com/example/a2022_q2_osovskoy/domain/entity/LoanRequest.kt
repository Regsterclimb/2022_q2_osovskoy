package com.example.a2022_q2_osovskoy.domain.entity

data class LoanRequest(
    val amount: Long,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
)