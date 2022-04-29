package com.example.a2022_q2_osovskoy.domain.use_case

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.model.MyCurrency
import com.example.a2022_q2_osovskoy.domain.model.toMyCurrency
import com.example.a2022_q2_osovskoy.domain.refactor.DoubleRounder
import com.example.a2022_q2_osovskoy.domain.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CurrencyUseCase {

    suspend fun load(id: String?): ResultState<MyCurrency>

    class Base(
        private val currencyRepository: CurrencyRepository,
        private val doubleRounder: DoubleRounder,
        private val dispatcherIO: CoroutineDispatcher,
    ) : CurrencyUseCase {
        override suspend fun load(id: String?): ResultState<MyCurrency> =
            withContext(dispatcherIO) {
                try {
                    ResultState.Success(data = currencyRepository.loadCurrency(id!!)!!
                        .toMyCurrency(doubleRounder))
                } catch (e: NullPointerException) {
                    Log.e("CurrencyUseCase", "NullPointer, cannot find this Id")
                    ResultState.Error()
                } catch (e: IllegalArgumentException) {
                    Log.e("CurrencyUseCase", "Mock or something IS GOES WRONG")
                    ResultState.Error()
                }
            }
    }
}