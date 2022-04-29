package com.example.a2022_q2_osovskoy.domain.use_case

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.model.MyCurrency
import com.example.a2022_q2_osovskoy.domain.model.toMyCurrency
import com.example.a2022_q2_osovskoy.domain.refactor.DoubleRounder
import com.example.a2022_q2_osovskoy.domain.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface CurrenciesUseCase {

    suspend fun load(): ResultState<List<MyCurrency>>

    class Base(
        private val currencyRepository: CurrencyRepository,
        private val doubleRounder: DoubleRounder,
        private val dispatcherIO: CoroutineDispatcher,
    ) : CurrenciesUseCase {
        override suspend fun load(): ResultState<List<MyCurrency>> = withContext(dispatcherIO) {
            try {
                ResultState.Success(data = currencyRepository.loadCurrencies()
                    .map { currencyDto -> currencyDto.toMyCurrency(doubleRounder) })
            } catch (e: HttpException) {
                Log.e("CurrenciesUseCase", "HTTP ERROR")
                ResultState.Error()
            } catch (e: RuntimeException) {
                Log.e("CurrenciesUseCase", "Mock or something IS GOES WRONG")
                ResultState.Error()
            }
        }
    }
}