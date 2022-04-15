package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.DispatchersRep
import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto
import com.example.a2022_q2_osovskoy.data.remote.network_module.NetworkModule
import com.example.a2022_q2_osovskoy.data.remote.responses.toCurrencyDto
import kotlinx.coroutines.withContext

class CurrencyDataRepositoryImpl(
    private val networkModule: NetworkModule,
    private val dispatcher: DispatchersRep
) : CurrencyDataRepository {

    override suspend fun loadDataCurrencyDtoList(): List<CurrencyDto> =
        withContext(dispatcher.getIO()) {
            networkModule.getAllData().currenciesMap.values.toList().map { currencyResponse ->
                currencyResponse.toCurrencyDto()
            }
        }
}