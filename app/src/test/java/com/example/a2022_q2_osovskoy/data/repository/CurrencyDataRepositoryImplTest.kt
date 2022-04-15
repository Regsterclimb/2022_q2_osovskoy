package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.DispatchersRep
import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto
import com.example.a2022_q2_osovskoy.data.remote.network_module.NetworkModule
import com.example.a2022_q2_osovskoy.data.remote.responses.ApiData
import com.example.a2022_q2_osovskoy.data.remote.responses.CurrencyResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class CurrenciesRepositoryImplTest {

    @Test
    fun `WHEN loadDataCurrencyDtoList Expect correct result`() = runBlocking {
        val apiData: ApiData = mock()
        val networkModule: NetworkModule = mock()

        val currencyMap = mapOf(
            "AUD" to CurrencyResponse(
                charCode = "AUD",
                id = "R01010",
                name = "Австралийский доллар",
                nominal = 1,
                numCode = "036",
                previous = 59.4941,
                value = 60.6246
            ), "AMD" to CurrencyResponse(
                charCode = "AMD",
                id = "R01060",
                name = "Армянских драмов",
                nominal = 100,
                numCode = "051",
                previous = 17.2399,
                value = 16.9014
            )
        )
        whenever(networkModule.getAllData()).thenReturn(apiData)
        whenever(apiData.currenciesMap).thenReturn(currencyMap)

        val currencyDataRepository = CurrencyDataRepositoryImpl(networkModule, DispatchersRep())

        val actual = currencyDataRepository.loadDataCurrencyDtoList()

        val expected = listOf(
            CurrencyDto(
                charCode = "AUD",
                id = "R01010",
                name = "Австралийский доллар",
                nominal = 1,
                numCode = "036",
                previous = 59.4941,
                value = 60.6246
            ), CurrencyDto(
                charCode = "AMD",
                id = "R01060",
                name = "Армянских драмов",
                nominal = 100,
                numCode = "051",
                previous = 17.2399,
                value = 16.9014
            )
        )

        assertEquals(expected, actual)
    }
}