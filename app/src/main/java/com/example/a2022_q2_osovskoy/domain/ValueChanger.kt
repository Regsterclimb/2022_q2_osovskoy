package com.example.a2022_q2_osovskoy.domain

import android.util.Log
import kotlin.math.roundToLong

interface ValueChanger {

    fun refactorValue(value: Double, nominal: Int): Double

    fun roundValue(value: Double): Double

    class Base : ValueChanger {
        private val roundDigit = 10_000.0

        private enum class Nominal(val nominal: Int, val value: Double) {
            ONE(1, 1.0),
            TEN(10, 10.0),
            ONE_HUNDRED(100, 100.0),
            ONE_THOUSAND(1_000, 1_000.0),
            TEN_THOUSAND(10_000, 10_000.0)
        }

        override fun refactorValue(value: Double, nominal: Int): Double =
            when (nominal) {
                Nominal.ONE.nominal -> value
                Nominal.TEN.nominal -> (value / Nominal.TEN.value)
                Nominal.ONE_HUNDRED.nominal -> (value / Nominal.ONE_HUNDRED.value)
                Nominal.ONE_THOUSAND.nominal -> (value / Nominal.ONE_THOUSAND.value)
                Nominal.TEN_THOUSAND.nominal -> (value / Nominal.TEN_THOUSAND.value)
                else -> {
                    Log.e("error", "something wrong with $nominal ")
                    throw RuntimeException("wrong Nominal $nominal")
                }
            }

        override fun roundValue(value: Double): Double =
            (value * roundDigit).roundToLong() / roundDigit
    }
}