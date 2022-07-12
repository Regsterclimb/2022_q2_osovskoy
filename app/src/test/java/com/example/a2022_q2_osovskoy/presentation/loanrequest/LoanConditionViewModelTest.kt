package com.example.a2022_q2_osovskoy.presentation.loanrequest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoanConditionUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateAppConfigUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class LoanConditionViewModelTest {

    private lateinit var getLoanConditionUseCase: GetLoanConditionUseCase
    private lateinit var updateAppConfigUseCase: UpdateAppConfigUseCase
    private lateinit var observer: Observer<LoanConditionState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        getLoanConditionUseCase = mock()
        updateAppConfigUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN loanConditionState Expect Success`() = runTest {
        val loanCondition = LoanCondition(45, 15000, 8.5)

        Mockito.`when`(getLoanConditionUseCase()).thenReturn(loanCondition)
        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Success(loanCondition)

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN showInstruction Expect  correct instructionState`() = runTest {

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        viewModel.showInstruction(false)

        val expected = Instruction(false)

        val actual = viewModel.instructionState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN updateConfig Expect correct result`() = runTest {
        val appConfig = AppConfig.BASE

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        viewModel.updateAppConfig(appConfig)

        verify(updateAppConfigUseCase, Mockito.times(1)).invoke(AppConfig.BASE)
    }

    @Test
    fun `WHEN loanConditionState Expect ErrorBadRequest`() = runTest {

        whenever(getLoanConditionUseCase()).thenThrow(BadRequestException())

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Error.BadRequest

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanConditionState Expect ErrorNotFound`() = runTest {

        whenever(getLoanConditionUseCase()).thenThrow(NotFoundException())

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Error.NotFound

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanConditionState Expect ErrorUnauthorized`() = runTest {

        whenever(getLoanConditionUseCase()).thenThrow(UnauthorizedException())

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Error.Unauthorized

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanConditionState Expect ErrorForbidden`() = runTest {

        whenever(getLoanConditionUseCase()).thenThrow(ForbiddenException())

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Error.Forbidden

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanConditionState Expect ErrorServerIsNotResponding`() = runTest {

        whenever(getLoanConditionUseCase()).thenThrow(ServerIsNotRespondingException())

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Error.ServerIsNotResponding

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanConditionState Expect ErrorServerNoInternetConnection`() = runTest {

        whenever(getLoanConditionUseCase()).thenAnswer { throw IOException() }

        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Error.NoInternetConnection

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

}