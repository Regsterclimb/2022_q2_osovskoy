package com.example.a2022_q2_osovskoy.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.usecase.GetAppConfigUseCase
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class SupportViewModelTest {

    lateinit var getConfigUseCase:GetAppConfigUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        getConfigUseCase = mock()
    }


    @Test
    fun `WHEN appAppConfigEvent Expect NavigateToRegistration`() = runTest {

        val appConfig = AppConfig.UNAUTHORIZED

        Mockito.`when`(getConfigUseCase()).thenReturn(appConfig)

        val viewModel = SupportViewModel(getConfigUseCase)

        val actual = viewModel.appStartScreenEvent.value

        val expected = StartScreenEvent.NavigateToRegistration(NavDestination.DEEP_REGISTRATION)

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN appAppConfigEvent Expect NavigateToLoanRequest`() = runTest {

        val appConfig = AppConfig.UNINSTRUCTED

        Mockito.`when`(getConfigUseCase()).thenReturn(appConfig)

        val viewModel = SupportViewModel(getConfigUseCase)

        val actual = viewModel.appStartScreenEvent.value

        val expected = StartScreenEvent.NavigateToLoanRequest(NavDestination.DEEP_LOAN_REQUEST)

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN appAppConfigEvent Expect NavigateToHistory`() = runTest {

        val appConfig = AppConfig.BASE

        Mockito.`when`(getConfigUseCase()).thenReturn(appConfig)

        val viewModel = SupportViewModel(getConfigUseCase)

        val actual = viewModel.appStartScreenEvent.value

        val expected = StartScreenEvent.NavigateToHistory(NavDestination.DEEP_HISTORY)

        assertEquals(expected, actual)
    }
}