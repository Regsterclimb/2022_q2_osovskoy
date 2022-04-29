package com.example.a2022_q2_osovskoy.kaspresso.test_case_1

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.kaspresso.screen.MainScreen
import com.example.a2022_q2_osovskoy.kaspresso.test_setting.KTestCase
import com.example.a2022_q2_osovskoy.kaspresso.tools.annotation.TestCase
import com.example.a2022_q2_osovskoy.kaspresso.tools.dispatcher.CurrencyCustomDispatcher
import com.example.a2022_q2_osovskoy.kaspresso.tools.test_model.CurrencyListTest
import com.example.a2022_q2_osovskoy.kaspresso.tools.test_model.toSecondaryCurrencyTest
import com.example.a2022_q2_osovskoy.presentation.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.properties.Delegates


@RunWith(AndroidJUnit4::class)
class MockActivityTest : KTestCase() {
    private companion object {
        const val PORT = 8080
    }

    private val mockWebServer = MockWebServer()
    private var currencyList: CurrencyListTest by Delegates.notNull()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @TestCase("Test-1", "check if currencies show Correct data on MainFragment screen")
    fun checkIfCurrenciesShowCorrectData() = before {
        currencyList = CurrencyListTest.Base()
        with(mockWebServer) {
            start(PORT)
            dispatcher =
                CurrencyCustomDispatcher.Base(InstrumentationRegistry.getInstrumentation().targetContext)
        }
        activityRule.scenario

    }.after {
        mockWebServer.shutdown()
    }
        .run {
            MainScreen {
                step("Check testCurrency content") {
                    checkCurrencies(currencyList)
                }
            }
        }
}

private fun checkCurrencies(listTest: CurrencyListTest) {
    listTest.loadList().forEachIndexed { index, currency ->
        MainScreen {
            currenciesList {
                childAt<MainScreen.Currency>(index) {
                    with(currency.toSecondaryCurrencyTest()) {
                        currencyName {
                            isDisplayed()
                            hasText(currencyName)
                        }
                        currencyValue {
                            isDisplayed()
                            hasText(currencyValue)
                        }
                    }
                }
            }
        }
    }
}
