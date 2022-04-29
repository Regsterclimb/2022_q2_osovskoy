package com.example.a2022_q2_osovskoy.domain.model

import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto
import com.example.a2022_q2_osovskoy.domain.refactor.DoubleRounder

data class MyCurrency(
    val charCode: String,
    val id: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val previous: Double,
    val value: Double,
)

fun CurrencyDto.toMyCurrency(doubleRounder: DoubleRounder): MyCurrency {
    return MyCurrency(
        id = id,
        charCode = charCode,
        name = name,
        nominal = nominal,
        numCode = numCode,
        previous = doubleRounder.roundFourDigit(previous - value),
        value = value,
    )
}



