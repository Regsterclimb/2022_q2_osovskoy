package com.example.a2022_q2_osovskoy.kaspresso

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.di.data.SharedPrefModule
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
class LoanDetailScreenTest : KTestCase() {

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
    @TestCase("LoanDetailScreen Test-1", "all views shown")
    fun checkViews() {
        run {
            step("open ApprovalDetail") {
                navToAuth()
                navToCondition()
                navToHistory()
                clickOnApprovalItem()
            }
            step("check") {
                LoanDetailScreen {
                    loanInfoTable.isDisplayed()
                    detailAmountRow {
                        isDisplayed()
                        hasText(R.string.detailAmountText)
                    }
                    detailAmount {
                        isDisplayed()
                        hasText("15000руб")
                    }
                    detailDateRow {
                        isDisplayed()
                        hasText(R.string.detailDateText)
                    }
                    detailDate {
                        scrollTo()
                        isDisplayed()
                        hasText("24.12.14")
                    }
                    detailPercentRow {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.detailPercentText)
                    }
                    detailPercent {
                        scrollTo()
                        isDisplayed()
                        hasText("8.5%")
                    }
                    detailPeriodRow {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.detailPeriod)
                    }
                    detailPeriod {
                        scrollTo()
                        isDisplayed()
                        hasText("45дн.")
                    }
                    detailIdRow {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.id)
                    }
                    detailId {
                        scrollTo()
                        isDisplayed()
                        hasText("144")
                    }
                    detailStateRow {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.detailStatus)
                    }
                    detailState {
                        scrollTo()
                        isDisplayed()
                        hasText("ОДОБРЕНА")
                    }
                    detailsNameRow {
                        isDisplayed()
                        hasText(R.string.detailsName)
                    }
                    detailsName {
                        scrollTo()
                        isDisplayed()
                        hasText("Олег")
                    }
                    detailsLastNameRow {
                        isDisplayed()
                        hasText(R.string.detailsLastName)
                    }
                    detailsLastName {
                        scrollTo()
                        isDisplayed()
                        hasText("Олегович")
                    }
                    detailsPhoneRow {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.detailsPhone)
                    }
                    detailsLastPhone {
                        scrollTo()
                        isDisplayed()
                        hasText("+1238493")
                    }
                    approvalContainer {
                        scrollTo()
                        isDisplayed()
                    }
                    detailCardImage {
                        scrollTo()
                        isDisplayed()
                        hasDrawable(R.drawable.card)
                    }
                    detailCardText {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.cardRequestText)
                    }
                    detailPassportImage {
                        scrollTo()
                        isDisplayed()
                        hasDrawable(R.drawable.passport)
                    }
                    detailPassportText {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.passportText)
                    }
                    handImage {
                        scrollTo()
                        isDisplayed()
                        hasDrawable(R.drawable.money)
                    }
                    detailHandText {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.getMoney)
                    }
                }
            }
        }
    }


    private fun clickOnApprovalItem() {
        LoanHistoryScreen {
            loansList.isDisplayed()
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
                scrollTo()
                typeText(nameInput)
                isDisplayed()
            }
            closeSoftKeyboard()
            authPasswordEdit {
                scrollTo()
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