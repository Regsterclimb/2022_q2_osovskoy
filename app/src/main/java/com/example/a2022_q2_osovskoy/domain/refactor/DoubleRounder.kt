package com.example.a2022_q2_osovskoy.domain.refactor

import kotlin.math.roundToLong

interface DoubleRounder {

    fun roundTwoDigit(value: Double): Double

    fun roundFourDigit(value: Double): Double

    fun roundThreeDigit(value: Double):Double

    class Base : DoubleRounder {
        private companion object {
            const val TEN_THOUSANDS = 10_000.0
            const val ONE_THOUSAND = 1_000.0
            const val ONE_HUNDRED = 100.0
        }

        override fun roundTwoDigit(value: Double): Double =
            (roundFourDigit(roundThreeDigit(value)) * ONE_HUNDRED).roundToLong() / ONE_HUNDRED

        override fun roundThreeDigit(value: Double): Double =
            (value * ONE_THOUSAND).roundToLong() / ONE_THOUSAND

        override fun roundFourDigit(value: Double): Double =
            (value * TEN_THOUSANDS).roundToLong() / TEN_THOUSANDS




    }
}