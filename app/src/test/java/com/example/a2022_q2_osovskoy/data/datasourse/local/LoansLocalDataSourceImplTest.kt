package com.example.a2022_q2_osovskoy.data.datasourse.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.LoansDao
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoansLocalDataSourceImplTest {

    private lateinit var loansDao: LoansDao

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        loansDao = mock()
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

        whenever(loansDao.getAll()).thenReturn(loansList)

        val localData = LoansLocalDataSourceImpl(loansDao)

        val expected = listOf(
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
            ))
        val actual = localData.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getById Expect correct Result`() = runTest {
        val loansDao: LoansDao = mock()

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

        whenever(loansDao.getById(145)).thenReturn(loanEntity)

        val localData = LoansLocalDataSourceImpl(loansDao)

        val expected = LoanEntity(135,
            "24.5.1992",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "1234513",
            "APPROVED",
            8.5
        )
        val actual = localData.getById(145)

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN insertAll Expect correct Result`() = runTest {

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

        val localData = LoansLocalDataSourceImpl(loansDao)

        localData.insertAll(loansList)

        val expected = listOf(
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
            ))
        verify(loansDao, Mockito.times(1)).insertAll(expected)
    }

    @Test
    fun `WHEN insertLoan Expect correct Result`() = runTest {

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

        val localData = LoansLocalDataSourceImpl(loansDao)

        localData.insertLoan(loanEntity)

        val expected = LoanEntity(135,
            "24.5.1992",
            "Олег",
            "Олегович",
            15000.0,
            45,
            "1234513",
            "APPROVED",
            8.5
        )

        verify(loansDao, Mockito.times(1)).insertLoan(expected)
    }
}
