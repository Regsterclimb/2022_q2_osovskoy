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
class RequestSuccessScreenTest : KTestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val cleanPrefs = InstrumentationRegistry.getInstrumentation()
        .targetContext.getSharedPreferences(SharedPrefModule.SHARED_PREFS_KEY,
            Context.MODE_PRIVATE).edit().clear().apply()

    @Before
    fun setupBefore() {
        cleanPrefs
    }

    @After
    fun setupAfter() {
        cleanPrefs
    }

    @Test
    @TestCase("RequestScreen Test-1", "correct views shown")
    fun checkViews() {
        run {
            step("nav to Success") {
                navToAuth()
                navToCondition()
                navToRequest()
                navToSuccess()
            }
            RequestSuccessScreen {
                step("check") {
                    loanSuccessImage.isDisplayed()
                    successTitle {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.successTitle)
                    }
                    successSecondary {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.requestSuccessText)
                    }
                    navToHistoryButton {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.requestHistoryButtonText)
                    }
                    navToDetailButton {
                        scrollTo()
                        isDisplayed()
                        hasText(R.string.requestDetailsButtonText)
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RequestScreen Test-2", "nav to Detail")
    fun navToDetail() {
        run {
            step("nav to Success") {
                navToAuth()
                navToCondition()
                navToRequest()
                navToSuccess()
            }
            RequestSuccessScreen {
                step("check") {
                    navToDetailButton {
                        scrollTo()
                        isDisplayed()
                        click()
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RequestScreen Test-3", "nav to History")
    fun navToHistory() {
        run {
            step("nav to Success") {
                navToAuth()
                navToCondition()
                navToRequest()
                navToSuccess()
            }
            RequestSuccessScreen {
                step("click") {
                    navToHistoryButton {
                        scrollTo()
                        isDisplayed()
                        click()
                    }
                }
                step("check history"){
                    LoanHistoryScreen{
                        logoutButton{
                            isDisplayed()
                        }
                    }
                }
            }
        }
    }

    private fun navToSuccess() {
        val nameInput = "Oleg"
        val lastName = "Lorem"
        val phone = "88005553535"

        LoanRequestScreen {
            loanNameEdit {
                isDisplayed()
                typeText(nameInput)
            }
            closeSoftKeyboard()
            loanLastNameEdit {
                isDisplayed()
                typeText(lastName)
            }
            closeSoftKeyboard()
            loanPhoneEdit {
                isDisplayed()
                typeText(phone)
            }
            closeSoftKeyboard()
            requestLoanButton {
                isDisplayed()
                click()
            }
        }
    }

    private fun navToRequest() {
        LoanConditionScreen {
            conditionList {
                scrollTo()
                childAt<LoanConditionScreen.LoanConditionHolder>(0) {
                    requestButton {
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

