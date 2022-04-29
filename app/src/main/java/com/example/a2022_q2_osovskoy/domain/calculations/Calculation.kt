package com.example.a2022_q2_osovskoy.domain.calculations

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.refactor.InputRefactor
import java.math.RoundingMode

interface Calculation {

    suspend fun start(inputNumberString: String, value: Double): String

    class Base(private val inputRefactor: InputRefactor<Any>) : Calculation {

        override suspend fun start(inputNumberString: String, value: Double): String = try {
            with(inputRefactor) {
                refactorValue(inputNumberString)
                    .divide(
                        (refactorValue(value)), 2, RoundingMode.HALF_UP
                    ).toString()
            }
        } catch (e: ArithmeticException) {
            Log.e("error", "деление на ноль Ошибка Calculate.start()")
            throw e
        } catch (e: RuntimeException) {
            Log.e("error", "Ошибка Calculate.start()")
            throw e
        }
    }
}