package com.example.a2022_q2_osovskoy.kaspresso

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.di.data.SharedPrefModule
import com.example.a2022_q2_osovskoy.kaspresso.model.LoanItemTest
import com.example.a2022_q2_osovskoy.kaspresso.screen.*
import com.example.a2022_q2_osovskoy.kaspresso.screen.annotation.TestCase
import com.example.a2022_q2_osovskoy.kaspresso.setting.KTestCase
import com.example.a2022_q2_osovskoy.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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
                loansList.isDisplayed()
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

    @Test
    @TestCase("LoanHistoryScreen Test-2", "exit from acc")
    fun checkExit() {
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
                        click()
                    }
                }
                step("check auth screen") {
                    AuthScreen {
                        authTitle {
                            isDisplayed()
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("LoanHistoryScreen Test-3", "nav to detail")
    fun checkNavToDetail() {
        run {
            step("nav to History") {
                navToAuth()
                navToCondition()
                navToHistory()
            }
            LoanHistoryScreen {
                step("click") {
                    clickOnFirstItem()
                }
            }
            step("check auth screen") {
                LoanDetailScreen {
                    loanInfoTable.isDisplayed()
                }
            }
        }
    }

    private fun clickOnFirstItem() {
        LoanHistoryScreen {
            loansList {
                childAt<LoanHistoryScreen.LoanItemHolder>(0) {
                    click()
                }
            }
        }
    }

    private fun navToHistory() {
        LoanConditionScreen {
            openHistory {
                scrollTo()
                isDisplayed()
                click()
            }
        }
    }

    private fun navToAuth() {
        RegScreen {
            authText {
                scrollTo()
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
                scrollTo()
                isDisplayed()
                click()
            }
        }
    }
}