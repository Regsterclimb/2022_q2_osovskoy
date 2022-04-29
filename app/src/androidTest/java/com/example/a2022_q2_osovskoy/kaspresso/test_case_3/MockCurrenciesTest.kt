package com.example.a2022_q2_osovskoy.kaspresso.test_case_3

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.kaspresso.screen.CurrencyChangeScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.MainScreen
import com.example.a2022_q2_osovskoy.kaspresso.test_setting.KTestCase
import com.example.a2022_q2_osovskoy.kaspresso.tools.annotation.TestCase
import com.example.a2022_q2_osovskoy.kaspresso.tools.dispatcher.CurrencyCustomDispatcher
import com.example.a2022_q2_osovskoy.kaspresso.tools.test_model.CurrencyListTest
import com.example.a2022_q2_osovskoy.kaspresso.tools.test_model.MainCurrencySecondaryTest
import com.example.a2022_q2_osovskoy.presentation.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.properties.Delegates

@RunWith(AndroidJUnit4::class)
class MockCurrenciesTest : KTestCase() {

    private companion object {
        const val PORT = 8080
    }

    private val mockWebServer = MockWebServer()
    private var currencyListTest: List<MainCurrencySecondaryTest> by Delegates.notNull()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @TestCase(name = "Test-4",
        description = "Check if click on currency shows correct result")
    fun checkNavigationWithCorrectCurrencyView() = before {
        currencyListTest = CurrencyListTest.Base().loadList()
        with(mockWebServer) {
            start(PORT)
            dispatcher =
                CurrencyCustomDispatcher.Base(
                    InstrumentationRegistry.getInstrumentation().targetContext)
        }
        activityRule.scenario
    }.after {
        mockWebServer.shutdown()
    }.run {
        step("Check clicks on CurrenciesRecycler(currenciesList) children") {
            MainScreen {
                currenciesList {
                    currencyListTest.forEachIndexed { index, testCurrencyChanger ->
                        childAt<MainScreen.Currency>(index) {
                            click()
                        }
                        step("Check correct currency displayed while currencyListTest size") {
                            checkCurrency(testCurrencyChanger)

                            step("Check back to mainScreen") {
                                CurrencyChangeScreen {
                                    backButton.click()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkCurrency(mainCurrency: MainCurrencySecondaryTest) {
        CurrencyChangeScreen {
            with(mainCurrency) {
                currencyName {
                    isDisplayed()
                    hasText(currencyName)
                }
                currencyValue {
                    isDisplayed()
                    hasText(currencyValue)

                }
                currencyCharCode {
                    isDisplayed()
                    hasText(charCode)
                }
            }
        }
    }
}