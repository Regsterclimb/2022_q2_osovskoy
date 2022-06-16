package com.example.a2022_q2_osovskoy.domain.entity.loan

data class LoanDetail(
    val name: String,
    val id: Long,
    val date: String,
    val amount : Double,
    val state: String,
    val percent: Double,
    val phoneNumber: String,
    val lastName: String,
    val period: Int,
)