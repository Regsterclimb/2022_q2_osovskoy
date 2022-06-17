package com.example.a2022_q2_osovskoy.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.repository.LocalLoansRepository
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
class GetLocalLoansUseCaseTest {

    lateinit var localLoansRepository: LocalLoansRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        localLoansRepository = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() = runTest {
        val loansList =
            listOf(Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5))

        whenever(localLoansRepository.getAll()).thenReturn(loansList)
        val useCase = GetLocalLoansUseCase(localLoansRepository)

        val expected =
            listOf(Loan(135, 15000.0, "25.12.2021", "APPROVED", 8.5))

        val actual = useCase()

        assertEquals(expected, actual)
    }
}