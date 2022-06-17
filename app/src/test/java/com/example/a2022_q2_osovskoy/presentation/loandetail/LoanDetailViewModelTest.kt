package com.example.a2022_q2_osovskoy.presentation.loandetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import com.example.a2022_q2_osovskoy.domain.usecase.GetLocalLoanByIdUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.GetRemoteLoanByIdUseCase
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoanDetailViewModelTest {

    lateinit var getLocalByIDLoanUseCase: GetLocalLoanByIdUseCase
    lateinit var getRemoteLoanByIdUseCase: GetRemoteLoanByIdUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var observer: Observer<LoanDetailState>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        getLocalByIDLoanUseCase = mock()
        getRemoteLoanByIdUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN getLocalLoan Expect loansState SuccessLocal`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "APPROVED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getLocalByIDLoanUseCase(145)).thenReturn(loanDetail)
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)
        viewModel.loanState.observeForever(observer)
        viewModel.getLocalLoan(145)


        val expected = LoanDetailState.Success.Local(
            LoanDetail(
                "Олег",
                145,
                "24.12.2012",
                15000.0, "APPROVED",
                8.5, "1234838",
                "Олегович",
                45
            )
        )
        verify(observer).onChanged(expected)
    }

    @Test
    fun `WHEN getLocalLoan Expect loansState APPROVED true`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "APPROVED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getLocalByIDLoanUseCase(145)).thenReturn(loanDetail)
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)
        viewModel.loanState.observeForever(observer)
        viewModel.getLocalLoan(145)

        val expected = LoanDetailState.Approved(true)


        val actual = viewModel.loanState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLocalLoan Expect loansState APPROVED false`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "REGISTERED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getLocalByIDLoanUseCase(145)).thenReturn(loanDetail)
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)
        viewModel.getLocalLoan(145)

        val expected = LoanDetailState.Approved(false)

        val actual = viewModel.loanState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLocalLoan Expect loansState ErrorUnknown`() = runTest {

        whenever(getLocalByIDLoanUseCase(145)).thenAnswer { throw Throwable() }
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)

        viewModel.getLocalLoan(145)

        val expected = LoanDetailState.Error.Unknown

        val actual = viewModel.loanState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getLocalLoan Expect loansState Loading`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "REGISTERED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getLocalByIDLoanUseCase(145)).thenReturn(loanDetail)
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)

        viewModel.loanState.observeForever(observer)
        viewModel.getRemoteLoan(145)

        val expected = LoanDetailState.Loading

        verify(observer).onChanged(expected)
    }

    @Test
    fun `WHEN getRemoteLoan Expect loansState SuccessRemote`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "APPROVED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getRemoteLoanByIdUseCase(145)).thenReturn(loanDetail)

        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)

        viewModel.loanState.observeForever(observer)
        viewModel.getRemoteLoan(145)

        val expected = LoanDetailState.Success.Remote(
            LoanDetail(
                "Олег",
                145,
                "24.12.2012",
                15000.0, "APPROVED",
                8.5, "1234838",
                "Олегович",
                45
            )
        )
        verify(observer).onChanged(expected)
    }

    @Test
    fun `WHEN getRemoteLoan Expect loansState APPROVED true`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "APPROVED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getRemoteLoanByIdUseCase(145)).thenReturn(loanDetail)
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)

        viewModel.getRemoteLoan(145)

        val expected = LoanDetailState.Approved(true)

        val actual = viewModel.loanState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getRemoteLoan Expect loansState APPROVED false`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "REGISTERED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getRemoteLoanByIdUseCase(145)).thenReturn(loanDetail)
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)

        viewModel.getRemoteLoan(145)

        val expected = LoanDetailState.Approved(false)

        val actual = viewModel.loanState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getRemoteLoan Expect loansState Loading`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "REGISTERED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(getRemoteLoanByIdUseCase(145)).thenReturn(loanDetail)
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)

        viewModel.loanState.observeForever(observer)
        viewModel.getRemoteLoan(145)

        val expected = LoanDetailState.Approved(false)

        verify(observer).onChanged(expected)
    }

    //todo()ошибки
    @Test
    fun `WHEN getRemoteLoan Expect loansState ErrorNoInternetConnection`() = runTest {

        whenever(getRemoteLoanByIdUseCase(145)).thenAnswer { throw IOException() }
        val viewModel = LoanDetailViewModel(getRemoteLoanByIdUseCase, getLocalByIDLoanUseCase)

        viewModel.getRemoteLoan(145)

        val expected = LoanDetailState.Error.NoInternetConnection

        val actual = viewModel.loanState.value

        assertEquals(expected, actual)
    }
}