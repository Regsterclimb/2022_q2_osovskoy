package com.example.a2022_q2_osovskoy.presentation.loanhistory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.usecase.GetLocalLoansUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.GetRemoteLoansUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateAppConfigUseCase
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanConditionState
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
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoansHistoryViewModelTest {

    lateinit var getLocalLoansUseCase: GetLocalLoansUseCase
    lateinit var updateAppConfigUseCase: UpdateAppConfigUseCase
    lateinit var getRemoteLoansUseCase: GetRemoteLoansUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var observer: Observer<LoanConditionState>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        updateAppConfigUseCase = mock()
        getLocalLoansUseCase = mock()
        getRemoteLoansUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN updateAppConfig Expect correct Result`() = runTest {
        val appConfig = AppConfig.BASE
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.updateAppConfig(appConfig)

        verify(updateAppConfigUseCase, Mockito.times(1)).invoke(AppConfig.BASE)
    }


    @Test
    fun `WHEN getLocalLoans Expect loansState SuccessLocal`() = runTest {
        val loansList =
            listOf(Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5))

        whenever(getLocalLoansUseCase()).thenReturn(loansList)
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.getLocalLoans()

        val expected = LoansState.Success.Local(loansList)

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLocalLoans Expect loansState Empty`() = runTest {
        val loansList = emptyList<Loan>()

        whenever(getLocalLoansUseCase()).thenReturn(loansList)
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.getLocalLoans()

        val expected = LoansState.Empty

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLocalLoans Expect loansState ErrorUnknown`() = runTest {

        whenever(getLocalLoansUseCase()).doAnswer { throw Throwable() }
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.getLocalLoans()

        val expected = LoansState.Error.Unknown

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState SuccessRemote`() = runTest {
        val loansList =
            listOf(Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5))

        whenever(getRemoteLoansUseCase()).thenReturn(loansList)
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Success.Remote(
            listOf(Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5))
        )

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState Empty`() = runTest {
        val loansList = emptyList<Loan>()

        whenever(getRemoteLoansUseCase()).thenReturn(loansList)
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Empty

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState ErrorNotFound`() = runTest {

        whenever(getRemoteLoansUseCase()).thenThrow(NotFoundException())
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Error.NotFound

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState ErrorBadRequest`() = runTest {

        whenever(getRemoteLoansUseCase()).thenThrow(BadRequestException())
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Error.BadRequest

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState ErrorForbidden`() = runTest {

        whenever(getRemoteLoansUseCase()).thenThrow(ForbiddenException())
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Error.Forbidden

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState ErrorServerIsNotResponding`() = runTest {

        whenever(getRemoteLoansUseCase()).thenThrow(ServerIsNotRespondingException())
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Error.ServerIsNotResponding

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState ErrorNoInternetConnection`() = runTest {

        whenever(getRemoteLoansUseCase()).thenAnswer { throw IOException() }
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Error.NoInternetConnection

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN refreshLoans Expect loansState ErrorUnauthorized`() = runTest {

        whenever(getRemoteLoansUseCase()).thenThrow(UnauthorizedException())
        val viewModel = LoansHistoryViewModel(
            getRemoteLoansUseCase,
            updateAppConfigUseCase,
            getLocalLoansUseCase)

        viewModel.refreshLoans()

        val expected = LoansState.Error.Unauthorized

        val actual = viewModel.loansState.value

        assertEquals(expected, actual)
    }
}