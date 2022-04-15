package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto

interface CurrencyDataRepository {

    suspend fun loadDataCurrencyDtoList(): List<CurrencyDto>
}