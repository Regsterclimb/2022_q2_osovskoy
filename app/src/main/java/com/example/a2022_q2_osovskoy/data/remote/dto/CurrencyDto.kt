package com.example.a2022_q2_osovskoy.data.remote.dto


data class CurrencyDto(
    val charCode: String,
    val id: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val previous: Double,
    val value: Double
)

