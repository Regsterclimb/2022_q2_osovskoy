package com.example.a2022_q2_osovskoy.data.remote.responses

import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    @SerialName("CharCode")
    val charCode: String,
    @SerialName("ID")
    val id: String,
    @SerialName("Name")
    val name: String,
    @SerialName("Nominal")
    val nominal: Int,
    @SerialName("NumCode")
    val numCode: String,
    @SerialName("Previous")
    val previous: Double,
    @SerialName("Value")
    val value: Double
)

fun CurrencyResponse.toCurrencyDto(): CurrencyDto {
    return CurrencyDto(
        charCode, id, name, nominal, numCode, previous, value
    )
}
