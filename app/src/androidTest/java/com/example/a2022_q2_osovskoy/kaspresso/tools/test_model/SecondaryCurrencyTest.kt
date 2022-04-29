@file:JvmName("MainCurrencySecondaryTestKt")

package com.example.a2022_q2_osovskoy.kaspresso.tools.test_model


open class SecondaryCurrencyTest(val currencyName: String, val currencyValue: String)

fun MainCurrencySecondaryTest.toSecondaryCurrencyTest() : SecondaryCurrencyTest = SecondaryCurrencyTest(
    currencyName = String.format("$currencyName $charCode"), currencyValue
)
