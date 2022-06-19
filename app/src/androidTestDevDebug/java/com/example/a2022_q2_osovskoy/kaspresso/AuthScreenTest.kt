package com.example.a2022_q2_osovskoy.kaspresso

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.di.data.SharedPrefModule
import com.example.a2022_q2_osovskoy.kaspresso.screen.AuthScreen
import com.example.a2022_q2_osovskoy.kaspresso.screen.LoanConditionScreen
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
class AuthScreenTest : KTestCase() {

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

    private fun openAuth() {
        RegScreen {
            authText {
                scrollTo()
                isDisplayed()
                click()
            }
        }
    }

    @Test
    @TestCase("AuthScreen Test-1", "all views shown")
    fun checkViews() {
        run {
            step("open Auth") {
                openAuth()
            }
            step("check if shown") {
                AuthScreen {
                    authTitle {
                        hasText(R.string.authTitle)
                        isDisplayed()
                    }
                    authSignUp {
                        hasText(R.string.authSignUp)
                        isDisplayed()
                    }
                    authNameInput {
                        scrollTo()
                        hasHint(R.string.inputName)
                        isDisplayed()
                    }
                    authNameEdit {
                        isDisplayed()
                    }
                    authPasswordInput {
                        scrollTo()
                        hasHint(R.string.inputPassword)
                        isDisplayed()
                    }
                    authPasswordEdit {
                        isDisplayed()
                    }
                    authButton {
                        scrollTo()
                        hasText(R.string.authButtonText)
                        isDisplayed()
                    }
                    regText {
                        scrollTo()
                        hasText(R.string.startAuthText)
                        isDisplayed()
                    }
                }
            }
        }
    }

    @Test
    @TestCase("AuthScreen Test-2", "all fields empty shows error")
    fun checkFieldsInputError() {
        val nameInput = ""
        val passwordInput = ""

        run {
            step("open Auth") {
                openAuth()
            }
            step("type fields") {
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
                    step("click") {
                        authButton {
                            scrollTo()
                            click()
                            isDisplayed()
                        }
                        step("check error") {
                            authNameInput {
                                scrollTo()
                                hasError(R.string.inputName)
                                isDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("AuthScreen Test-3", "name empty show error")
    fun checkNameInputError() {
        val nameInput = ""
        val passwordInput = "12345"

        run {
            step("open Auth") {
                openAuth()
            }
            step("type fields") {
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
                    step("click") {
                        authButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            authNameInput {
                                scrollTo()
                                hasError(R.string.inputName)
                                isDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("AuthScreen Test-4", "empty password shows error")
    fun checkPasswordInputError() {
        val nameInput = "Oleg"
        val passwordInput = ""

        run {
            step("open Auth") {
                openAuth()
            }
            step("type fields") {
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
                    step("click") {
                        authButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            authPasswordInput {
                                scrollTo()
                                hasError(R.string.inputPassword)
                                isDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("AuthScreen Test-5", "nav to RegScreen")
    fun checkNavToReg() {
        run {
            step("open Auth") {
                openAuth()
            }
            step("click on reg") {
                AuthScreen {
                    regText {
                        scrollTo()
                        isDisplayed()
                        click()
                    }
                    step("check nav") {
                        RegScreen {
                            regTitle {
                                scrollTo()
                                hasText(R.string.regTitle)
                                isDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("AuthScreen Test-6", "success")
    fun checkNavSuccess() {
        val nameInput = "Oleg"
        val passwordInput = "1234"
        run {
            step("open Auth") {
                openAuth()
            }
            step("type fields") {
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
                    step("click") {
                        authButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                    }
                    step("check nav") {
                        LoanConditionScreen {
                            conditionTitle.isDisplayed()
                        }
                    }
                }
            }
        }
    }
}