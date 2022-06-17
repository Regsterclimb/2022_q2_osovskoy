package com.example.a2022_q2_osovskoy.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
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
class RequestLoanUseCaseTest {

    private lateinit var remoteLoansRepository: RemoteLoansRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        remoteLoansRepository = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() = runTest {
        val request = LoanRequest(
            15000L,
            "Олег",
            "Олегович",
            8.5,
            45,
            "123456"
        )
        val loan = Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5)

        whenever(remoteLoansRepository.requestLoan(request)).thenReturn(loan)
        val useCase = RequestLoanUseCase(remoteLoansRepository)

        val expected = Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5)

        val actual = useCase(
            LoanRequest(
                15000L,
                "Олег",
                "Олегович",
                8.5,
                45,
                "123456"
            )
        )

        assertEquals(expected, actual)
    }
}