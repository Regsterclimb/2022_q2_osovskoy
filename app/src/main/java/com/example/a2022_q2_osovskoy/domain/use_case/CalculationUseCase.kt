package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.domain.calculations.Calculation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CalculationUseCase {

    suspend fun calculate(inputNumberString: String, value: Double): ResultState<String>

    class Base(
        private val calculation: Calculation,
        private val dispatcherDefault: CoroutineDispatcher,
    ) : CalculationUseCase {

        override suspend fun calculate(
            inputNumberString: String,
            value: Double,
        ): ResultState<String> = withContext(dispatcherDefault) {
            try {
                ResultState.Success(data = calculation.start(inputNumberString, value))
            } catch (e: ArithmeticException) {
                ResultState.Error()
            } catch (e: RuntimeException) {
                ResultState.Error()
            }
        }
    }
}