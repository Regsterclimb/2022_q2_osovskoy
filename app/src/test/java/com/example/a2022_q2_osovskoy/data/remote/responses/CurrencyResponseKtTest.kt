package com.example.a2022_q2_osovskoy.data.remote.responses

import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto
import org.junit.Assert.assertEquals
import org.junit.Test


class CurrencyResponseKtTest {

    @Test
    fun `WHEN toCurrencyDto Expect correct result`() {
        val currencyResponse = CurrencyResponse(
            charCode = "AMD",
            id = "R01060",
            name = "Армянских драмов",
            nominal = 100,
            numCode = "051",
            previous = 17.2399,
            value = 16.9014
        )

        val actual = currencyResponse.toCurrencyDto()

        val expected = CurrencyDto(
            charCode = "AMD",
            id = "R01060",
            name = "Армянских драмов",
            nominal = 100,
            numCode = "051",
            previous = 17.2399,
            value = 16.9014
        )

        assertEquals(actual, expected)
    }
}