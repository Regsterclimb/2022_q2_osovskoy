package com.example.a2022_q2_osovskoy.kaspresso

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.di.data.SharedPrefModule
import com.example.a2022_q2_osovskoy.kaspresso.screen.AuthScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.LoanConditionScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.LoanHistoryScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.RegScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.annotation.TestCase
import com.example.a2022_q2_osovskoy.kaspresso.setting.KTestCase
import com.example.a2022_q2_osovskoy.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoanHistoryScreenTest : KTestCase() {

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
    @TestCase("LoanHistoryScreen Test-1", "all views shown")
    fun checkViews() {
        run {
            step("nav to History") {
                navToAuth()
                navToCondition()
                navToHistory()
            }
            LoanHistoryScreen {
                step("check") {
                    logoutButton {
                        isDisplayed()
                        hasText(R.string.exit)
                    }
                    applyNewLoanButton {
                        isDisplayed()
                        hasText(R.string.applyLoanButton)
                    }
                    historyLoanId {
                        isDisplayed()
                        hasText(R.string.historyId)
                    }
                    historyDateAndAmount {
                        isDisplayed()
                        hasText(R.string.historyDateAmount)
                    }
                    historyStatus {
                        isDisplayed()
                        hasText(R.string.status)
                    }
                    checkLoans(loanItemTestList)
                }
            }
        }
    }

    private val loanItemTestList = listOf(
        LoanItemTest("144", "24.12.14", "15000руб", "ОДОБРЕНА"),
        LoanItemTest("145", "24.12.14", "15000руб", "ПРИНЯТА"),
        LoanItemTest("146", "24.12.14", "15000руб", "ОТКЛОНЕНА")
    )

    private fun checkLoans(listTest: List<LoanItemTest>) {
        listTest.forEachIndexed { index, loan ->
            LoanHistoryScreen {
                loansList {
                    childAt<LoanHistoryScreen.LoanItemHolder>(index) {
                        loanItemId {
                            isDisplayed()
                            hasText(loan.id)
                        }
                        loanItemStatus {
                            isDisplayed()
                            hasText(loan.state)
                        }
                        loanItemDate {
                            isDisplayed()
                            hasText(loan.date)
                        }
                        loanItemAmount {
                            isDisplayed()
                            hasText(loan.amount)
                        }
                    }
                }
            }
        }
    }

    private fun navToHistory() {
        LoanConditionScreen {
            openHistory {
                isDisplayed()
                click()
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