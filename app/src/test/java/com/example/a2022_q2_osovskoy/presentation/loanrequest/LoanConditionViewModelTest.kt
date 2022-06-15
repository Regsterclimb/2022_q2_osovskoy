package com.example.a2022_q2_osovskoy.presentation.loanrequest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoanConditionUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateAppConfigUseCase
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
class LoanConditionViewModelTest {

    lateinit var getLoanConditionUseCase: GetLoanConditionUseCase
    lateinit var updateAppConfigUseCase: UpdateAppConfigUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var observer: Observer<LoanConditionState>

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        getLoanConditionUseCase = mock()
        updateAppConfigUseCase = mock()
        observer = mock()
    }

    @Test
    fun `WHEN loanConditionState Expect LoadConditionStateSuccess`() = runTest {
        val loanCondition = LoanCondition(45, 15000, 8.5)

        Mockito.`when`(getLoanConditionUseCase()).thenReturn(loanCondition)
        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Success(loanCondition)

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }
    //todo()
    @Test
    fun `WHEN loanConditionState Expect LoadConditionStateError`() = runTest {
        /*val resultState: ResultState<LoanCondition> = ResultState.Error()

        Mockito.`when`(getLoanConditionUseCase()).thenReturn(resultState)*/
        val viewModel = LoanConditionViewModel(updateAppConfigUseCase, getLoanConditionUseCase)

        val expected = LoanConditionState.Success(LoanCondition(45, 15000, 8.5))

        val actual = viewModel.loanCondition.value

        assertEquals(expected, actual)
    }
}