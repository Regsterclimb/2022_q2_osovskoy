package com.example.a2022_q2_osovskoy.domain.entity.loan

data class Loan(
    val id: Long,
    val amount: Double,
    val date: String,
    val state: String,
    val percent: Double,
) {
}