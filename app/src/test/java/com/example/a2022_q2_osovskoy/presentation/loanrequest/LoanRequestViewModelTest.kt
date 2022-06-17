package com.example.a2022_q2_osovskoy.presentation.loanrequest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.RequestLoanUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.BadRequestException
import com.example.a2022_q2_osovskoy.utils.exceptions.NotFoundException
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
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoanRequestViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var requestLoanUseCase: RequestLoanUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        requestLoanUseCase = mock()
    }

    @Test
    fun `WHEN loanRequestState Expect LoanConditionReceived `() = runTest {
        val period = 45
        val maxAmount = 15000L
        val percent = "8.5"

        val viewModel = LoanRequestViewModel(requestLoanUseCase)

        viewModel.setLoanCondition(maxAmount, percent, period)

        val expected = LoanCondition(45, 15000, 8.5)

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanRequestState Expect InputErrorName`() = runTest {
        val loanCondition = LoanCondition(45, 15000L, 8.5)
        val name = ""
        val lastname = "Олегович"
        val phone = "12345678"

        val viewModel = LoanRequestViewModel(requestLoanUseCase)

        viewModel.trySendRequest(loanCondition, name, lastname, phone)

        val expected = LoanRequestState.InputError.Name

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanRequestState Expect InputErrorLastName`() = runTest {
        val loanCondition = LoanCondition(45, 15000L, 8.5)
        val name = "Олег"
        val lastname = ""
        val phone = "12345678"

        val viewModel = LoanRequestViewModel(requestLoanUseCase)
        viewModel.trySendRequest(loanCondition, name, lastname, phone)


        val expected = LoanRequestState.InputError.LastName

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanRequestState Expect InputErrorPhone`() = runTest {
        val loanCondition = LoanCondition(45, 15000L, 8.5)
        val name = "Олег"
        val lastname = "Олегович"
        val phone = ""

        val viewModel = LoanRequestViewModel(requestLoanUseCase)
        viewModel.trySendRequest(loanCondition, name, lastname, phone)

        val expected = LoanRequestState.InputError.Phone

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }
    //todo()
    @Test
    fun `WHEN loanRequestState Expect Success `() = runTest {
        val loanRequest = LoanRequest(
            15000L,
            "Олег",
            "Олегович",
            8.5,
            45,
            "123456"
        )

        val loan = Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5)

        whenever(requestLoanUseCase(loanRequest)).thenReturn(loan)
        val viewModel = LoanRequestViewModel(requestLoanUseCase)

        viewModel.trySendRequest(
            LoanCondition(45, 15000L, 8.5),
            "Олег",
            "Олегович",
            "123456"
        )

        val expected = LoanRequestState.Success(
            Loan(135,15000.0,"25.12.2021", "APPROVED", 8.5)
        )

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN loanRequestState Expect ErrorBadRequest`() = runTest {
        val loanRequest = LoanRequest(
            15000L,
            "Олег",
            "Олегович",
            8.5,
            45,
            "123456"
        )

        whenever(requestLoanUseCase(loanRequest)).thenThrow(BadRequestException())
        val viewModel = LoanRequestViewModel(requestLoanUseCase)

        viewModel.trySendRequest(
            LoanCondition(45, 15000L, 8.5),
            "Олег",
            "Олегович",
            "123456"
        )

        val expected = LoanRequestState.Error.BadRequest

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }

    //todo() доделать errors
    @Test
    fun `WHEN loanRequestState Expect ErrorNotFound`() = runTest {
        val loanRequest = LoanRequest(
            15000L,
            "Олег",
            "Олегович",
            8.5,
            45,
            "123456"
        )

        whenever(requestLoanUseCase(loanRequest)).thenThrow(NotFoundException())
        val viewModel = LoanRequestViewModel(requestLoanUseCase)

        viewModel.trySendRequest(
            LoanCondition(45, 15000L, 8.5),
            "Олег",
            "Олегович",
            "123456"
        )

        val expected = LoanRequestState.Error.NotFound

        val actual = viewModel.loanRequestState.value

        assertEquals(expected, actual)
    }

}