package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto
import com.example.a2022_q2_osovskoy.data.remote.dto.toCurrencyDto
import com.example.a2022_q2_osovskoy.data.remote.network_module.NetworkModule
import com.example.a2022_q2_osovskoy.data.storage.CurrencyData
import com.example.a2022_q2_osovskoy.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val networkModule: NetworkModule,
    private val currencyData: CurrencyData,
) : CurrencyRepository {

    companion object {
        const val ONE_NOMINAL = 1
    }

    override suspend fun loadCurrencies(): List<CurrencyDto> {
        val list = networkModule.loadCurrenciesData().currenciesMap.values.toList().asSequence()
            .filter { currencyResponse -> currencyResponse.nominal == ONE_NOMINAL }
            .map { currencyResponse -> currencyResponse.toCurrencyDto() }.toList()
        currencyData.save(list)
        return list
    }

    override suspend fun loadCurrency(id: String?): CurrencyDto? = id?.let {
        currencyData.getCurrencyDto(currencyId = it)
    }
}