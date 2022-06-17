package com.example.a2022_q2_osovskoy.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.data.datasourse.local.LoansLocalDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import kotlinx.coroutines.CoroutineDispatcher
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
class LocalLoansRepositoryImplTest {

    private lateinit var localDataSource: LoansLocalDataSource
    private lateinit var dispatcher: CoroutineDispatcher

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        localDataSource = mock()
        dispatcher = Dispatchers.Unconfined
    }

    @Test
    fun `WHEN getAll Expect correct Result`() = runTest {
        val loansList = listOf(
            LoanEntity(
                135,
                "24.5.1992",
                "Олег",
                "Олегович",
                15000.0,
                45,
                "1234513",
                "APPROVED",
                8.5
            )
        )

        whenever(localDataSource.getAll()).thenReturn(loansList)

        val localRep = LocalLoansRepositoryImpl(localDataSource, dispatcher)

        val expected = listOf(
            Loan(
                135,
                15000.0,
                "24.5.1992",
                "APPROVED",
                8.5
            )
        )

        val actual = localRep.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getById Expect correct Result`() = runTest {
        val loanEntity = LoanEntity(
            135,
            "24.5.1992",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "1234513",
            "APPROVED",
            8.5
        )

        whenever(localDataSource.getById(135)).thenReturn(loanEntity)

        val localRep = LocalLoansRepositoryImpl(localDataSource, dispatcher)

        val expected = LoanDetail(
            "Олег",
            135,
            "24.5.1992",
            15000.0,
            "APPROVED",
            8.5,
            "1234513",
            "Олегович",
            45
            )

        val actual = localRep.getById(135)

        assertEquals(expected, actual)
    }
}