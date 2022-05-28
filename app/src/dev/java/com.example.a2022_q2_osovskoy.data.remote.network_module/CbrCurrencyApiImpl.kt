package a2022_q2_osovskoy.data.remote.network_module

import com.example.a2022_q2_osovskoy.data.remote.network_module.CbrCurrencyApi
import com.example.a2022_q2_osovskoy.data.remote.responses.ApiData
import com.example.a2022_q2_osovskoy.data.remote.responses.CurrencyResponse
import retrofit2.mock.BehaviorDelegate

class CbrCurrencyApiImpl(private val delegate: BehaviorDelegate<CbrCurrencyApi>) :
    CbrCurrencyApi {

    override suspend fun loadDataFromApi(): ApiData {
        val apiData = ApiData(
            date = "asdsd",
            previousDate = "asdasd",
            previousURL = "asdasd",
            timestamp = "asdasd",
            currenciesMap = currencyMap
        )
        return delegate.returningResponse(apiData).loadDataFromApi()
    }

    private val currencyMap = mapOf(
        "AUD" to CurrencyResponse(
            id = "R01010",
            numCode = "036",
            charCode = "AUD",
            nominal = 1,
            name = "Австралийский доллар",
            value = 52.4958,
            previous = 53.8104),
        "SSS" to CurrencyResponse(
            id = "R01010",
            numCode = "036",
            charCode = "AUD",
            nominal = 1,
            name = "Австралийский доллар",
            value = 52.4958,
            previous = 53.8104),
        "DDD" to CurrencyResponse(
            id = "R01010",
            numCode = "036",
            charCode = "AUD",
            nominal = 1,
            name = "Австралийский доллар",
            value = 52.4958,
            previous = 53.8104),
        "GGG" to CurrencyResponse(
            id = "R01010",
            numCode = "036",
            charCode = "AUD",
            nominal = 1,
            name = "Австралийский доллар",
            value = 52.4958,
            previous = 53.8104),
        "AAA" to CurrencyResponse(
            id = "R01010",
            numCode = "036",
            charCode = "AUD",
            nominal = 1,
            name = "Австралийский доллар",
            value = 52.4958,
            previous = 53.8104),
        "DSD" to CurrencyResponse(
            id = "R01010",
            numCode = "036",
            charCode = "AUD",
            nominal = 1,
            name = "Австралийский доллар",
            value = 52.4958,
            previous = 53.8104),
    )
}