package com.example.a2022_q2_osovskoy.kaspresso

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.di.data.SharedPrefModule
import com.example.a2022_q2_osovskoy.kaspresso.screen.AuthScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.LoanConditionScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.LoanRequestScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.RegScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.annotation.TestCase
import com.example.a2022_q2_osovskoy.kaspresso.setting.KTestCase
import com.example.a2022_q2_osovskoy.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoanConditionScreenTest : KTestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val clearPrefs = InstrumentationRegistry.getInstrumentation()
        .targetContext.getSharedPreferences(SharedPrefModule.SHARED_PREFS_KEY,
            Context.MODE_PRIVATE).edit().clear().apply()

    @Before
    fun setup() {
        clearPrefs
    }

    @After
    fun exit() {
        clearPrefs
    }

    @Test
    @TestCase("LoanConditionScreen Test-1", "all views shown")
    fun checkViews() {
        run {
            step("nav to Condition") {
                navToAuth()
                navToCondition()
            }
            step("check") {
                LoanConditionScreen {
                    showInstructionButton {
                        hasText(R.string.instruction)
                        isDisplayed()
                    }
                    openHistory {
                        isDisplayed()
                        hasText(R.string.loanConditionHistoryButton)
                    }
                    instructionTab.isVisible()
                    conditionTitle {
                        hasText(R.string.loanConditionTitle)
                        isDisplayed()
                    }
                    checkConditions(loanConditionList)
                }
            }
        }
    }
    private val loanConditionList =
        listOf(LoanConditionTest("45дн.", "15000руб", "8.5%"))

    private fun checkConditions(listTest: List<LoanConditionTest>) {

        listTest.forEachIndexed { index, condition ->
            LoanConditionScreen {
                conditionList {
                    childAt<LoanConditionScreen.LoanConditionHolder>(index) {
                        requestAmountRow {
                            isDisplayed()
                            hasText(R.string.conditionAmountRow)
                        }
                        bannerContainer.isDisplayed()
                        mainImage.isDisplayed()
                        requestPercentRow {
                            isDisplayed()
                            hasText(R.string.conditionPercentRow)
                        }
                        requestPeriodRow {
                            isDisplayed()
                            hasText(R.string.conditionPeriodRow)
                        }
                        mainAmount {
                            isDisplayed()
                            hasText(condition.amount)
                        }
                        mainPercent {
                            isDisplayed()
                            hasText(condition.percent)
                        }
                        mainPeriod {
                            isDisplayed()
                            hasText(condition.period)
                        }
                        requestButton {
                            isDisplayed()
                            hasText(R.string.openLoanScreenText)
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("LoanConditionScreen Test-2", "hide instruction on click")
    fun hideInstructionOnClick() {
        run {
            step("nav to Condition") {
                navToAuth()
                navToCondition()
            }
            LoanConditionScreen {
                step("check visible") {
                    instructionTab.isDisplayed()
                }
                step("click") {
                    showInstructionButton {
                        hasText(R.string.instruction)
                        isDisplayed()
                        click()
                    }
                }
                step("check if hide") {
                    instructionTab.isNotDisplayed()
                }
            }
        }
    }

    @Test
    @TestCase("LoanConditionScreen Test-3", "clickOnRequest")
    fun clickOnRequest() {
        run {
            step("nav to Condition") {
                navToAuth()
                navToCondition()
            }
            step("click onRequest") {
                LoanConditionScreen {
                    conditionList {
                        childAt<LoanConditionScreen.LoanConditionHolder>(0) {
                            requestButton {
                                isDisplayed()
                                click()
                            }
                        }

                    }
                }
            }
            step("check nav") {
                LoanRequestScreen {
                    requestTitle.isDisplayed()
                }
            }
        }
    }

    @Test
    @TestCase("LoanConditionScreen Test-3", "clickOnRequest")
    fun navToHistory() {
        run {
            step("nav to Condition") {
                navToAuth()
                navToCondition()
            }
            step("click OpenHistory") {
                LoanConditionScreen {
                    openHistory {
                        isDisplayed()
                        click()
                    }
                }
            }
        }
    }
    private fun navToAuth() {
        RegScreen {
            authText {
                isDisplayed()
                click()
            }
        }
    }

    private fun navToCondition() {
        val nameInput = "Oleg"
        val passwordInput = "1234"
        AuthScreen {
            authNameEdit {
                typeText(nameInput)
                isDisplayed()
            }
            closeSoftKeyboard()
            authPasswordEdit {
                typeText(passwordInput)
                isDisplayed()
            }
            closeSoftKeyboard()
            authButton {
                isDisplayed()
                click()
            }
        }
    }
}