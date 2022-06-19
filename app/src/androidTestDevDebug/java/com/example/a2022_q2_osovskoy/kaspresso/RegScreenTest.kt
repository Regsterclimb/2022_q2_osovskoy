package com.example.a2022_q2_osovskoy.kaspresso

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.di.data.SharedPrefModule
import com.example.a2022_q2_osovskoy.kaspresso.screen.AuthScreen
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
class RegScreenTest : KTestCase() {

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
    @TestCase("RegScreen Test-1", "all views shown")
    fun checkRegScreen() {
        run {
            step("check if shown") {
                RegScreen {
                    regTitle {
                        hasText(R.string.regTitle)
                        isDisplayed()
                    }
                    regSignUp {
                        scrollTo()
                        hasText(R.string.regUp)
                        isDisplayed()
                    }
                    regNameInput {
                        scrollTo()
                        hasHint(R.string.inputName)
                        isDisplayed()
                    }
                    regNameEdit {
                        isDisplayed()
                    }
                    regPasswordInput {
                        scrollTo()
                        hasHint(R.string.inputPassword)
                        isDisplayed()
                    }
                    regNameEdit {
                        isDisplayed()
                    }
                    regButton {
                        scrollTo()
                        hasText(R.string.registrationButtonText)
                        isDisplayed()
                    }
                    authText {
                        scrollTo()
                        hasText(R.string.startRegistrationText)
                        isDisplayed()
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RegScreen Test-2", "all fields empty shows error")
    fun checkFieldsInputError() {
        val nameInput = ""
        val passwordInput = ""

        run {
            step("type empty") {
                RegScreen {
                    regNameEdit {
                        scrollTo()
                        typeText(nameInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    regPasswordEdit {
                        scrollTo()
                        typeText(passwordInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    step("click") {
                        regButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            regNameInput {
                                scrollTo()
                                isDisplayed()
                                hasError(R.string.inputName)
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RegScreen Test-3", "name empty show error")
    fun checkNameInputError() {
        val nameInput = ""
        val passwordInput = "12345"

        run {
            step("type fields") {
                RegScreen {
                    regNameEdit {
                        typeText(nameInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    regPasswordEdit {
                        scrollTo()
                        typeText(passwordInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    step("click") {
                        regButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            regNameInput {
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
    @TestCase("RegScreen Test-4", "empty password shows error")
    fun checkPasswordInputError() {
        val nameInput = "Oleg"
        val passwordInput = ""

        run {
            step("type fields") {
                RegScreen {
                    regNameEdit {
                        typeText(nameInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    regPasswordEdit {
                        typeText(passwordInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    step("click") {
                        regButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                        step("check error") {
                            regPasswordInput {
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
    @TestCase("RegScreen Test-5", "success nav to AuthScreen")
    fun checkSuccess() {
        val nameInput = "Oleg"
        val passwordInput = "1234"

        run {
            step("type fields") {
                RegScreen {
                    regNameEdit {
                        typeText(nameInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    regPasswordEdit {
                        typeText(passwordInput)
                        isDisplayed()
                    }
                    closeSoftKeyboard()
                    step("click") {
                        regButton {
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                    }
                    step("check navigation") {
                        AuthScreen {
                            authTitle {
                                hasText(R.string.authTitle)
                                isDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase("RegScreen Test-6", "nav to AuthScreen")
    fun checkNavToAuth() {
        run {
            step("type fields") {
                RegScreen {
                    step("click"){
                        authText{
                            scrollTo()
                            isDisplayed()
                            click()
                        }
                    }
                    step("check navigation") {
                        AuthScreen {
                            authTitle {
                                scrollTo()
                                hasText(R.string.authTitle)
                                isDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }
}