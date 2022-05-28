package com.example.a2022_q2_osovskoy.presentation

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.a2022_q2_osovskoy.data.remote.network_module.NetworkModule
import com.example.a2022_q2_osovskoy.data.repository.CurrencyRepositoryImpl
import com.example.a2022_q2_osovskoy.data.storage.CurrencyData
import com.example.a2022_q2_osovskoy.domain.calculations.Calculation
import com.example.a2022_q2_osovskoy.domain.refactor.DoubleRounder
import com.example.a2022_q2_osovskoy.domain.refactor.InputRefactor
import com.example.a2022_q2_osovskoy.domain.use_case.CalculationUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.CurrenciesUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.CurrencyUseCase
import kotlinx.coroutines.Dispatchers
import kotlin.properties.Delegates

open class App : Application() {

    var sharedPreferences: SharedPreferences by Delegates.notNull()
    var currencyUseCase: CurrencyUseCase by Delegates.notNull()
    var currenciesUseCase: CurrenciesUseCase by Delegates.notNull()
    var calculationUseCase: CalculationUseCase by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

        val currencyRepository = CurrencyRepositoryImpl(NetworkModule.RetrofitModule(),
            CurrencyData.Base(sharedPreferences))

        currencyUseCase = CurrencyUseCase.Base(currencyRepository,
            DoubleRounder.Base(),
            getDispatcherMainImmediate())
        currenciesUseCase = CurrenciesUseCase.Base(currencyRepository,
            DoubleRounder.Base(),
            getDispatcherMainImmediate())
        calculationUseCase =
            CalculationUseCase.Base(Calculation.Base(InputRefactor.Base()), getDispatcherDefault())
    }

    open fun getDispatcherMainImmediate() = Dispatchers.Main.immediate
    open fun getDispatcherIO() = Dispatchers.IO
    open fun getDispatcherDefault() = Dispatchers.Default

    open fun getBaseUrl() = BASE_URL

    companion object {
        const val BASE_URL = "https://www.cbr-xml-daily.ru"
        const val SHARED_PREFERENCES = "mySharedPrefs"
    }
}