package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto

interface CurrencyRepository {

    suspend fun loadCurrencies(): List<CurrencyDto>

    suspend fun loadCurrency(id : String?) : CurrencyDto?

}