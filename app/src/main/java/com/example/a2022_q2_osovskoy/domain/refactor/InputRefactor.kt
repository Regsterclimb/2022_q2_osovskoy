package com.example.a2022_q2_osovskoy.domain.refactor

import java.math.BigDecimal

interface InputRefactor<T> {

    fun refactorValue(data: T): BigDecimal

    class Base<T> : InputRefactor<T> {
        override fun refactorValue(data: T): BigDecimal =
            when (data) {
                is String -> data.toBigDecimal()
                is Double -> data.toBigDecimal()
                else -> throw IllegalArgumentException()
            }
    }
}