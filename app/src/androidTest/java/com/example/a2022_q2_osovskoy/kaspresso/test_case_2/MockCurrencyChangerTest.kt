package com.example.a2022_q2_osovskoy.kaspresso.test_case_2

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.kaspresso.screen.CurrencyChangeScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.MainScreen
import com.example.a2022_q2_osovskoy.kaspresso.test_setting.KTestCase
import com.example.a2022_q2_osovskoy.kaspresso.tools.annotation.TestCase
import com.example.a2022_q2_osovskoy.kaspresso.tools.dispatcher.CurrencyCustomDispatcher
import com.example.a2022_q2_osovskoy.presentation.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MockCurrencyChangerTest : KTestCase() {

    private companion object {
        const val PORT = 8080
    }

    private val mockWebServer = MockWebServer()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val settings
        get() = before {
            with(mockWebServer) {
                start(PORT)
                dispatcher = CurrencyCustomDispatcher.Base(
                    InstrumentationRegistry.getInstrumentation().targetContext)
            }
            activityRule.scenario
        }.after {
            mockWebServer.shutdown()
        }

    @Test
    @TestCase("Test-2", "check if edit text show error after calculation")
    fun checkErrorAfterCalculation() = settings.run {
        step("open fragment") {
            MainScreen {
                currenciesList {
                    childAt<MainScreen.Currency>(1) {
                        click()
                    }
                }
            }
        }
        step("input empty string and click changeButton expected error") {
            CurrencyChangeScreen {
                editText {
                    isDisplayed()
                    typeText("")
                }
                changeButton {
                    isDisplayed()
                    click()
                }
                step("check result changer") {
                    result {
                        isDisplayed()
                        hasText(R.string.resultLoadingError)
                    }
                }
            }
        }
    }

    private val currencyPosition = 1
    private val textInput = "25134.34"
    private val textResult = "587.66"

    @Test
    @TestCase("Test-3", "check if edit text show success")
    fun checkSuccess() = settings.run {
        step("open CurrencyChangeScreen screen") {
            MainScreen {
                currenciesList {
                    childAt<MainScreen.Currency>(currencyPosition) {
                        click()
                    }
                }
            }
        }
        step("input empty string and click changeButton") {
            CurrencyChangeScreen {
                editText {
                    isDisplayed()
                    typeText(textInput)
                }
                closeSoftKeyboard()
                changeButton {
                    isDisplayed()
                    click()
                }
                step("check result text") {
                    result {
                        isDisplayed()
                        hasText(textResult)
                    }
                }
            }
        }
    }
}