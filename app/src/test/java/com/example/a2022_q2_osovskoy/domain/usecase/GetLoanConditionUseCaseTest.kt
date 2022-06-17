package com.example.a2022_q2_osovskoy.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.repository.RemoteLoansRepository
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
class GetLoanConditionUseCaseTest {

    lateinit var remoteLoansRepository: RemoteLoansRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        remoteLoansRepository = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() = runTest {
        val loanCondition = LoanCondition(45, 15000, 8.3)

        whenever(remoteLoansRepository.getLoanCondition()).thenReturn(loanCondition)
        val useCase = GetLoanConditionUseCase(remoteLoansRepository)

        val expected = LoanCondition(45, 15000, 8.3)

        val actual = useCase()

        assertEquals(expected, actual)
    }
}