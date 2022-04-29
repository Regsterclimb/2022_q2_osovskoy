package com.example.a2022_q2_osovskoy.data.remote.dto


import com.example.a2022_q2_osovskoy.data.remote.responses.CurrencyResponse
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDto(
    val charCode: String,
    val id: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val previous: Double,
    val value: Double
)

fun CurrencyResponse.toCurrencyDto(): CurrencyDto {
    return CurrencyDto(
        charCode, id, name, nominal, numCode, previous, value
    )
}

