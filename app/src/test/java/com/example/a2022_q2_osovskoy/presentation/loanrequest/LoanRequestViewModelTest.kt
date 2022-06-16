package com.example.a2022_q2_osovskoy.presentation.loanrequest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.RequestLoanUseCase
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class LoanRequestViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var requestLoanUseCase: RequestLoanUseCase

    lateinit var observer: Observer<LoanRequestState>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        requestLoanUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN loanRequestState Expect LoanConditionReceived `() = runTest {
        val period = 45
        val maxAmount = 15000L
        val percent = "8.5"

        val viewModel = LoanRequestViewModel(requestLoanUseCase)

        viewModel.setLoanCondition(maxAmount, percent, period)
        viewModel.loanRequestState.observeForever(observer)

        val expected = LoanRequestState.LoanConditionReceived(LoanCondition(45, 15000, 8.5))

        verify(observer).onChanged(expected)
    }

    @Test
    fun `WHEN loanRequestState Expect InputErrorName`() = runTest {
        val period = 45
        val maxAmount = 15000L
        val percent = 8.5
        val name = ""
        val lastname = "Олегович"
        val phone = "12345678"

        val viewModel = LoanRequestViewModel(requestLoanUseCase)

        viewModel.trySendRequest(maxAmount, percent, period, name, lastname, phone)

        val expected = LoanRequestState.InputError.Name

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanRequestState Expect InputErrorLastName`() = runTest {
        val period = 45
        val maxAmount = 15000L
        val percent = 8.5
        val name = "Олег"
        val lastname = ""
        val phone = "12345678"

        val viewModel = LoanRequestViewModel(requestLoanUseCase)
        viewModel.trySendRequest(maxAmount, percent, period, name, lastname, phone)


        val expected = LoanRequestState.InputError.LastName

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanRequestState Expect InputErrorPhone`() = runTest {
        val period = 45
        val maxAmount = 15000L
        val percent = 8.5
        val name = "Олег"
        val lastname = "Олегович"
        val phone = ""

        val viewModel = LoanRequestViewModel(requestLoanUseCase)
        viewModel.trySendRequest(maxAmount, percent, period, name, lastname, phone)

        val expected = LoanRequestState.InputError.Phone

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }
        //todo()
    @Test
    fun `WHEN loanRequestState Expect Error`() = runTest {
        val period = 45
        val maxAmount = 15000L
        val percent = 8.5
        val name = "Олег"
        val lastname = "Олегович"
        val phone = "123456"

        val loanRequest = LoanRequest(maxAmount, name, lastname, percent, period, phone)

        /*val resultState: ResultState<Loan> = ResultState.Error()

        Mockito.`when`(requestLoanUseCase(loanRequest)).thenReturn(resultState)*/
        val viewModel = LoanRequestViewModel(requestLoanUseCase)
        viewModel.loanRequestState.observeForever(observer)

        viewModel.trySendRequest(maxAmount, percent, period, name, lastname, phone)

        val expected = LoanRequestState.Error

        verify(observer).onChanged(LoanRequestState.Loading)
        verify(observer).onChanged(expected)
    }
    //todo()
    @Test
    fun `WHEN loanRequestState Expect Success `() = runTest {
        val period = 45
        val maxAmount = 15000L
        val percent = 8.5
        val name = "Олег"
        val lastname = "Олегович"
        val phone = "123456"

        val loanRequest = LoanRequest(maxAmount, name, lastname, percent, period, phone)
        val loan = Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5)

        /*val resultState: ResultState<Loan> = ResultState.Success(loan)
        Mockito.`when`(requestLoanUseCase(loanRequest)).thenReturn(resultState)*/

        val viewModel = LoanRequestViewModel(requestLoanUseCase)
        viewModel.loanRequestState.observeForever(observer)

        viewModel.trySendRequest(maxAmount, percent, period, name, lastname, phone)

        val expected = LoanRequestState.Success(loan)

        verify(observer).onChanged(LoanRequestState.Loading)
        verify(observer).onChanged(expected)
    }
}