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
class RequestLoanScreenTest : KTestCase() {

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
            step("nav to Auth") {
                navToAuth()
            }
            step("nav to condition"){
                navToCondition()
            }
            step("nav to Request"){
                navToRequest()
            }
            step("check") {
                LoanRequestScreen {
                    requestTitle {
                        isDisplayed()
                        hasText(R.string.requestTitleText)
                    }
                    requestTab.isDisplayed()
                    requestAmountRow {
                        isDisplayed()
                        hasText(R.string.requestAmountRow)
                    }
                    requestAmount {
                        isDisplayed()
                        hasText("15000руб")
                    }
                    requestPercentRow {
                        isDisplayed()
                        hasText(R.string.requestPercentRow)
                    }
                    requestPercent {
                        isDisplayed()
                        hasText("8.5%")
                    }
                    requestPeriodRow {
                        isDisplayed()
                        hasText(R.string.requestPeriodRow)
                    }
                    requestPeriod {
                        isDisplayed()
                        hasText("45дн.")
                    }
                    loanNameInput {
                        isDisplayed()
                        hasHint(R.string.loanInputName)
                    }
                    loanNameEdit.isDisplayed()
                    loanLastNameInput {
                        isDisplayed()
                        hasHint(R.string.loanInputLastName)
                    }
                    loanLastNameEdit.isDisplayed()
                    loanPhoneInput {
                        isDisplayed()
                        hasHint(R.string.loanInputPhone)
                    }
                    loanPhoneEdit.isDisplayed()
                    requestLoanButton {
                        isDisplayed()
                        hasText(R.string.requestButtonText)
                    }
                    requestErrorText.isNotDisplayed()
                    requestProgressBar.isNotDisplayed()
                }
            }
        }
    }

    @Test
    @TestCase("RequestScreen Test-2", "all fields empty shows error")
    fun checkFieldsInputError() {
        val nameInput = ""
        val lastName = ""
        val phone = ""

        run {
            step("nav to Auth") {
                navToAuth()
            }
            step("nav to condition"){
                navToCondition()
            }
            step("nav to Request"){
                navToRequest()
            }
            step("type fields") {
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
                    step("click") {
                        requestLoanButton {
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            loanNameInput {
                                isDisplayed()
                                hasError(R.string.inputNameEmpty)
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RequestScreen Test-3", "show name error")
    fun checkNameInputError() {
        val nameInput = ""
        val lastName = "Lorem"
        val phone = "1234"

        run {
            step("nav to Auth") {
                navToAuth()
            }
            step("nav to condition"){
                navToCondition()
            }
            step("nav to Request"){
                navToRequest()
            }
            step("type fields") {
                LoanRequestScreen {
                    loanNameEdit {
                        isDisplayed()
                        typeText(nameInput)
                    }
                    closeSoftKeyboard()
                    loanLastNameEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(lastName)
                    }
                    closeSoftKeyboard()
                    loanPhoneEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(phone)
                    }
                    closeSoftKeyboard()
                    step("click") {
                        requestLoanButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            loanNameInput {
                                scrollTo()
                                isDisplayed()
                                hasError(R.string.inputNameEmpty)
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RequestScreen Test-4", "show lastName error")
    fun checkLastNameInputError() {
        val nameInput = "Oleg"
        val lastName = ""
        val phone = "1234"

        run {
            step("nav to Auth") {
                navToAuth()
            }
            step("nav to condition"){
                navToCondition()
            }
            step("nav to Request"){
                navToRequest()
            }
            step("type fields") {
                LoanRequestScreen {
                    loanNameEdit {
                        isDisplayed()
                        typeText(nameInput)
                    }
                    closeSoftKeyboard()
                    loanLastNameEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(lastName)
                    }
                    closeSoftKeyboard()
                    loanPhoneEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(phone)
                    }
                    closeSoftKeyboard()
                    step("click") {
                        requestLoanButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            loanLastNameInput {
                                scrollTo()
                                isDisplayed()
                                hasError(R.string.inputLastNameEmpty)
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RequestScreen Test-5", "show phone error")
    fun checkPhoneInputError() {
        val nameInput = "Oleg"
        val lastName = "Lorem"
        val phone = ""

        run {
            step("nav to Auth") {
                navToAuth()
            }
            step("nav to condition"){
                navToCondition()
            }
            step("nav to Request"){
                navToRequest()
            }
            step("type fields") {
                LoanRequestScreen {
                    loanNameEdit {
                        isDisplayed()
                        typeText(nameInput)
                    }
                    closeSoftKeyboard()
                    loanLastNameEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(lastName)
                    }
                    closeSoftKeyboard()
                    loanPhoneEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(phone)
                    }
                    closeSoftKeyboard()
                    step("click") {
                        requestLoanButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            loanPhoneInput {
                                scrollTo()
                                isDisplayed()
                                hasError(R.string.inputPhoneEmpty)
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RequestScreen Test-5", "show phone error")
    fun checkSuccessNav() {
        val nameInput = "Oleg"
        val lastName = "Lorem"
        val phone = "88005553535"

        run {
            step("nav to Auth") {
                navToAuth()
            }
            step("nav to condition"){
                navToCondition()
            }
            step("nav to Request"){
                navToRequest()
            }
            step("type fields") {
                LoanRequestScreen {
                    loanNameEdit {
                        isDisplayed()
                        typeText(nameInput)
                    }
                    closeSoftKeyboard()
                    loanLastNameEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(lastName)
                    }
                    closeSoftKeyboard()
                    loanPhoneEdit {
                        scrollTo()
                        isDisplayed()
                        typeText(phone)
                    }
                    closeSoftKeyboard()
                    step("click") {
                        requestLoanButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("nav to Success") {
                            RequestSuccessScreen{
                                successTitle{
                                    isDisplayed()
                                }
                            }
                        }
                    }
                }
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