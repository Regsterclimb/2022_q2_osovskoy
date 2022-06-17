package com.example.a2022_q2_osovskoy.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
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
class GetLocalLoanByIdUseCaseTest {

    private lateinit var localLoansRepository: LocalLoansRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        localLoansRepository = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() = runTest {
        val loanDetail = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "REGISTERED",
            8.5, "1234838",
            "Олегович",
            45
        )

        whenever(localLoansRepository.getById(145)).thenReturn(loanDetail)
        val useCase = GetLocalLoanByIdUseCase(localLoansRepository)

        val expected = LoanDetail(
            "Олег",
            145,
            "24.12.2012",
            15000.0, "REGISTERED",
            8.5, "1234838",
            "Олегович",
            45
        )

        val actual = useCase(145)

        assertEquals(expected, actual)
    }
}