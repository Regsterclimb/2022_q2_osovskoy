package com.example.a2022_q2_osovskoy.kaspresso.tools.test_model

interface CurrencyListTest {

    fun loadList(): List<MainCurrencySecondaryTest>

    class Base : CurrencyListTest {
        override fun loadList(): List<MainCurrencySecondaryTest> =
            listOf(MainCurrencySecondaryTest(currencyName = "Австралийский доллар",
                currencyValue = "52.4958", "AUD"),
                MainCurrencySecondaryTest(currencyName = "Азербайджанский манат",
                    currencyValue = "42.7699", "AZN"),
                MainCurrencySecondaryTest(currencyName = "Фунт стерлингов Соединенного королевства",
                    currencyValue = "92.4639", "GBP"),
                MainCurrencySecondaryTest(currencyName = "Белорусский рубль",
                    currencyValue = "27.3352", "BYN"),
                MainCurrencySecondaryTest(currencyName = "Болгарский лев",
                    currencyValue = "40.4725", "BGN"),
                MainCurrencySecondaryTest(currencyName = "Бразильский реал",
                    currencyValue = "14.8948", "BRL"),
                MainCurrencySecondaryTest(currencyName = "Датская крона",
                    currencyValue = "10.503", "DKK"),
                MainCurrencySecondaryTest(currencyName = "Доллар США",
                    currencyValue = "72.7089", "USD")
            )
    }
}